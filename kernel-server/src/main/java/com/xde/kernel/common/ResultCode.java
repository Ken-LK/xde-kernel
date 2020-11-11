package com.xde.kernel.common;

/**
 * 状态枚举类
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/30 9:05 上午
 **/
public enum ResultCode {
    /**
     * 成功返回
     */
    OK(1,"成功"),
    /**
     * 参数无效返回
     */
    PARAM_IS_INVALID(1001,"参数无效");


    private Integer code;

    private String message;

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }




}
