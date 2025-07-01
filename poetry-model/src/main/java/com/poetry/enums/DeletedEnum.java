package com.poetry.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除状态枚举
 * 0=未删除，1=已删除
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {

    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    private final int code;
    private final String description;

    public static DeletedEnum fromCode(int code) {
        for (DeletedEnum deleted : values()) {
            if (deleted.code == code) {
                return deleted;
            }
        }
        throw new IllegalArgumentException("未知删除状态 code: " + code);
    }
}
