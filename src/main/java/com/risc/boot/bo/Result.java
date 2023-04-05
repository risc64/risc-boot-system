package com.risc.boot.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回封装--泛型
 * @author 李良发
 * @version 1.0.0
 * @date 2020-06-10 16:04
 * @since 1.0.0
 **/
@Data
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private int status;
    
    /**
     * 状态信息
     */
    private String msg;


    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回异常信息
     */
    private Object exception;
    
    public Result() {
        this.setStatus(StatusEnum.OK.getKey());
        this.setMsg(StatusEnum.OK.getMsg());
    }

    public Result(StatusEnum statusEnum, T data) {
        this.setStatus(statusEnum.getKey());
        this.setMsg(statusEnum.getMsg());
        if (data != null) {
            this.data = data;
        }
    }
    
    public void setStatusEnum(StatusEnum statusEnum, T data) {
        this.setStatus(statusEnum.getKey());
        this.setMsg(statusEnum.getMsg());
        if (data != null) {
            this.data = data;
        }
    }

    /**
     * 异常
     * @param exception
     */
    public void exception(Object exception) {
        this.setStatus(StatusEnum.EXCEPTION.getKey());
        this.setMsg(StatusEnum.EXCEPTION.getMsg());
        this.setException(exception);
    }
    
}
