package com.xde.auth.api;

/**
 * 封装API的错误码
 * @author Ken
 */
public interface IErrorCode {

    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回内容
     */
    String getMessage();
}
