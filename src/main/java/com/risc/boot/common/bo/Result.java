package com.risc.boot.common.bo;

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
    
    /**
     * 返回简单的成功信息
     */
    public void success(){
        this.setStatus(StatusEnum.OK.getKey());
        this.setMsg(StatusEnum.OK.getMsg());
    }
    
    /**
     * 返回带有数据的成功信息
     * @param data
     */
    public void success(T data){
        this.setStatus(StatusEnum.OK.getKey());
        this.setMsg(StatusEnum.OK.getMsg());
        if (data != null) {
            this.data = data;
        }
    }
    
    /**
     * 返回没有数据的成功信息
     * 没有数据时不能返回错误
     */
    public void noData(){
        this.setStatus(StatusEnum.NOT_DATA.getKey());
        this.setMsg(StatusEnum.NOT_DATA.getMsg());
    }
    
    /**
     * 返回简单的错误信息
     */
    public void error(){
        this.setStatus(StatusEnum.ERROR.getKey());
        this.setMsg(StatusEnum.ERROR.getMsg());
    }
    
    /**
     * 返回带自定义消息的错误信息
     * @param msg
     */
    public void error(String msg){
        this.setStatus(StatusEnum.ERROR.getKey());
        this.setMsg(msg);
    }
    
    /**
     * 返回枚举错误类型信息
     * @param statusEnum
     */
    public void error(StatusEnum statusEnum){
        this.setStatus(statusEnum.getKey());
        this.setMsg(statusEnum.getMsg());
    }
    
    /**
     * 返回带有自定义消息的枚举错误类型信息
     * @param statusEnum
     * @param msg
     */
    public void error(StatusEnum statusEnum, String msg){
        this.setStatus(statusEnum.getKey());
        this.setMsg(msg);
    }
    
}
