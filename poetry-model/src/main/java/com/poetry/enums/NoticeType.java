package com.poetry.enums;

import lombok.Getter;

/**
 * @author moZiA
 */

@Getter
public enum NoticeType {
  INFO("info", "信息"),
  WARNING("warning", "警告"),
  ERROR("error", "错误"),
  SUCCESS("success", "成功");

  private final String code;
  private final String description;

  NoticeType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public static NoticeType fromCode(String code) {
    for (NoticeType type : values()) {
      if (type.code.equalsIgnoreCase(code)) {
        return type;
      }
    }
    throw new IllegalArgumentException("未知公告类型: " + code);
  }
}
