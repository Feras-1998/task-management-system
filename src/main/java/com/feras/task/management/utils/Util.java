package com.feras.task.management.utils;

public class Util {
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidId(Long id) {
        return id != null && id > 0;
    }

    public static boolean isInValidId(Long id) {
        return !isValidId(id);
    }
}
