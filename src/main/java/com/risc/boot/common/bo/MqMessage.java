package com.risc.boot.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: 李良发
 * @since: 2023/8/21
 */
@Data
public class MqMessage implements Serializable {
    
    /**
     * 主类型
     */
    private String mainType;
    
    /**
     * 子类型
     */
    private String subType;
    
    /**
     * 消息内容
     */
    private String msg;
    
}
