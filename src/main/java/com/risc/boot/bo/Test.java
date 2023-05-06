package com.risc.boot.bo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 测试表(Test)实体类
 *
 * @author 李良发
 * @since 2023-05-06 17:44:56
 */
@Data
public class Test implements Serializable {

    private static final long serialVersionUID = 936827648666701479L;
    
    /**
    * uid
    */
    private String uid;
    
    /**
    * 姓名
    */
    private String name;
    
    /**
    * 性别（0男 1女）
    */
    private Integer sex;
    
    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    


}
