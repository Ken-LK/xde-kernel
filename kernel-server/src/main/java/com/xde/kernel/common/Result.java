package com.xde.kernel.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 共同返回类
 *
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/30 9:03 上午
 **/
@Data
public class Result implements Serializable {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息描述
     */
    private String message;

    /**
     * 返回值
     */
    private Object data;


    /**
     * 返回成功
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.OK.code());
        return result;
    }


}
