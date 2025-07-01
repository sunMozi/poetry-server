package com.poetry.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型枚举
 * 0=admin，1=管理员，2=普通用户
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    ADMIN(0, "超级管理员"),
    MANAGER(1, "管理员"),
    USER(2, "普通用户");

    private final int code;
    private final String description;

    public static UserTypeEnum fromCode(int code) {
        for (UserTypeEnum type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知用户类型 code: " + code);
    }
}
