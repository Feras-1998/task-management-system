package com.feras.task.management.controller.common;

import com.feras.task.management.config.JwtUtil;
import com.feras.task.management.exception.user.exceptions.UserNotFoundException;
import com.feras.task.management.model.User;
import com.feras.task.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        String jwtToken = getToken();
        String username = jwtToken.isEmpty() ? null : jwtUtil.extractUsername(jwtToken);
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    private String getToken() {
        String tokenHeader = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getHeader("Authorization");

        Assert.isTrue(!tokenHeader.isEmpty(), "Invalid Token");
        tokenHeader = tokenHeader.replace("\"", "");
        Assert.isTrue(tokenHeader.startsWith("Bearer "), "Invalid Token");
        return tokenHeader.substring(7);
    }
}
