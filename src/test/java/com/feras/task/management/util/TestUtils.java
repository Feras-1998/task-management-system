package com.feras.task.management.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class TestUtils {
//    protected static final String USERNAME = "username";

    protected <T, R> R invokePrivateDeclaredMethod(
            Class<T> clazz, String methodName, Class<?>[] parameterTypes, T instance, Object... args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getPrivateDeclaredMethod(clazz, methodName, parameterTypes);
        return (R) method.invoke(instance, args);
    }

    private <T> Method getPrivateDeclaredMethod(Class<T> clazz, String methodName, Class<?>[] parameterTypes)
            throws NoSuchMethodException {
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method;
    }

    protected int generateRandomNumber() {
        return new Random().nextInt(100) + 1;
    }

//    protected String generateRandomToken() {
//        return TestJwtTokenGenerator.generateRandomToken();
//    }
//
//    private static final class TestJwtTokenGenerator {
//        private static final JwtUtil JWT_UTIL = new JwtUtil();
//
//        public static String generateRandomToken() {
//            return JWT_UTIL.generateToken(USERNAME);
//        }
//    }
}
