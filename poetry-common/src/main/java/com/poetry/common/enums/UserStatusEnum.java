package com.poetry.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
 * 是否启用：0=否，1=是
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    DISABLED(0, "否"),
    ENABLED(1, "是");

    private final int code;
    private final String description;

    public static UserStatusEnum fromCode(int code) {
        for (UserStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知用户状态 code: " + code);
    }
}
