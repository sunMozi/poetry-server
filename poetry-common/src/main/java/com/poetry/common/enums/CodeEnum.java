package com.poetry.common.enums;

import com.poetry.common.exception.Code;

/**
 * 响应枚举
 * @author moZiA
 */
public enum CodeEnum implements Code {

  // 成功
  SUCCESS(200, "成功！"),

  // 客户端参数错误
  PARAMETER_ERROR(400, "参数异常！"),

  // 认证 & 授权相关
  NOT_LOGIN(401, "未登录，请登录后再操作！"),
  LOGIN_EXPIRED(402, "登录已过期，请重新登录！"),
  ACCESS_DENIED(403, "没有权限访问该资源！"),
  ACCOUNT_LOCKED(406, "账号已被锁定，请联系管理员！"),
  ACCOUNT_DISABLED(407, "账号已被禁用，请联系管理员！"),
  ACCOUNT_NOT_EXIST(408, "账号不存在！"),
  PASSWORD_INCORRECT(409, "密码错误！"),

  // 系统状态
  SYSTEM_REPAIR(503, "系统维护中，敬请期待！"),

  // 通用失败
  FAIL(500, "服务异常！");

  private final int code;
  private final String msg;

  CodeEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public String getMsg() {
    return msg;
  }
}
