package com.poetry.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poetry.common.enums.CodeEnum;
import com.poetry.common.exception.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用接口响应对象
 *
 * @author Zyan
 * @created 2024-06-21 14:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

  /**
   * 响应编码
   */
  private int code;

  /**
   * 响应描述
   */
  private String message;

  /**
   * 响应数据
   */
  private T data;

  /**
   * 响应时间戳（毫秒）
   * 用于前后端日志对齐、性能监控
   */
  private long currentTimeMillis;

  /**
   * 接口响应失败
   *
   * @param code 响应码
   * @param <T>  数据类型
   * @return ResponseResult
   */
  public static <T> ResponseResult<T> error(Code code) {
    return common(code, null);
  }

  /**
   * 接口响应失败，仅含自定义消息
   *
   * @param message 自定义错误信息
   * @param <T> 数据类型
   * @return ResponseResult
   */
  public static <T> ResponseResult<T> error(String message) {
    ResponseResult<T> result = new ResponseResult<>();
    result.code = CodeEnum.FAIL.getCode();
    result.message = message;
    result.currentTimeMillis = System.currentTimeMillis();
    return result;
  }


  /**
   * 接口响应成功（无数据）
   *
   * @param <T> 数据类型
   * @return ResponseResult
   */
  public static <T> ResponseResult<T> ok() {
    return common(CodeEnum.SUCCESS, null);
  }

  /**
   * 接口响应成功（含数据）
   *
   * @param data 数据
   * @param <T>  数据类型
   * @return ResponseResult
   */
  public static <T> ResponseResult<T> ok(T data) {
    return common(CodeEnum.SUCCESS, data);
  }

  /**
   * 通用构建响应
   *
   * @param code 响应码
   * @param data 数据
   * @param <T>  数据类型
   * @return ResponseResult
   */
  private static <T> ResponseResult<T> common(Code code, T data) {
    return new ResponseResult<>(code, data);
  }

  /**
   * 构造方法
   *
   * @param code 响应码
   * @param data 数据
   */
  private ResponseResult(Code code, T data) {
    this.code = code.getCode();
    this.message = code.getMsg();
    this.data = data;
    this.currentTimeMillis = System.currentTimeMillis();
  }


}
