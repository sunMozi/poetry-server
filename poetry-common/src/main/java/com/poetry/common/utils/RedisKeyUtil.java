package com.poetry.common.utils;

public class RedisKeyUtil {
    private static final String PREFIX = "poetry";

    public static String userTokenKey(Long userId) {
        return String.format("%s:user:token:%d:access", PREFIX, userId);
    }

    public static String adminTokenKey(Long userId) {
        return String.format("%s:admin:token:%d:access", PREFIX, userId);
    }
}
