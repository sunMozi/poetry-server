package com.poetry.common.exception;

/**
 * @author zby
 * @created 2024-06-18 2:22
 * @description 枚举通用接口
 */
public interface Code {

    /**
     * 获取响应码
     *
     * @return 响应码
     */
    int getCode();

    /**
     * 获取响应码描述信息
     *
     * @return 响应码描述信息
     */
    String getMsg();

}
