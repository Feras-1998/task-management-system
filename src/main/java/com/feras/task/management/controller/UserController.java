package com.feras.task.management.controller;

import com.feras.task.management.dto.user.AuthenticationResponseBody;
import com.feras.task.management.dto.user.UserRequestBody;
import com.feras.task.management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("apis/user")
@Tag(name = "User Management", description = "User Management APIs")
public class UserController {
    private static final String USER_REQUEST_BODY_EXAMPLE = "{\"username\":\"feras\",\"password\":\"pass123\"}";
    private final UserService userService;

    @PutMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    private ResponseEntity<String> registerUser(
            @Parameter(description = "User registration request", example = USER_REQUEST_BODY_EXAMPLE)
            @RequestBody UserRequestBody request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/authenticate")
    @Operation(
            summary = "Authenticate a user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Authentication successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    private ResponseEntity<AuthenticationResponseBody> authenticate(
            @Parameter(description = "User registration request", example = USER_REQUEST_BODY_EXAMPLE)
            @RequestBody UserRequestBody request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
