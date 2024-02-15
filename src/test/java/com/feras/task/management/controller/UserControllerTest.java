package com.feras.task.management.controller;

import com.feras.task.management.dto.user.AuthenticationResponseBody;
import com.feras.task.management.dto.user.UserRequestBody;
import com.feras.task.management.service.UserService;
import com.feras.task.management.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserControllerTest extends TestUtils {
    private static final UserRequestBody USER_REQUEST_BODY = new UserRequestBody("FerasTest1", "Feras_10203040");

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testRegisterUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        doNothing().when(userService).register(USER_REQUEST_BODY);

        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok("User registered successfully");
        ResponseEntity<String> actualResponseEntity = invokePrivateDeclaredMethod(
                UserController.class,
                "registerUser",
                new Class[]{UserRequestBody.class},
                userController,
                USER_REQUEST_BODY);

        assertEquals(expectedResponseEntity, actualResponseEntity);

        verify(userService, times(1)).register(USER_REQUEST_BODY);
    }

    @Test
    void testAuthenticate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AuthenticationResponseBody expectedResponseBody = new AuthenticationResponseBody("token");

        when(userService.authenticate(USER_REQUEST_BODY)).thenReturn(expectedResponseBody);

        ResponseEntity<AuthenticationResponseBody> expectedResponseEntity = ResponseEntity.ok(expectedResponseBody);
        ResponseEntity<AuthenticationResponseBody> actualResponseEntity =
                invokePrivateDeclaredMethod(UserController.class, "authenticate",
                        new Class[]{UserRequestBody.class}, userController, USER_REQUEST_BODY);

        assertEquals(expectedResponseEntity, actualResponseEntity);

        verify(userService, times(1)).authenticate(USER_REQUEST_BODY);
    }
}
