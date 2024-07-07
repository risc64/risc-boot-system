package com.risc.boot.modules.system.bo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 角色表(SysRole)实体类
 *
 * @author 李良发
 * @since 2023-05-08 11:00:08
 */
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 541742583114980610L;
    
    /**
    * 角色uid
    */
    private String uid;
    
    /**
    * 角色名称
    */
    private String roleName;
    
    /**
    * 角色编码
    */
    private String roleCode;
    
    /**
    * 描述
    */
    private String description;
    
    /**
    * (0表示不可删除 1表示可删除）
    */
    private Integer deleteableFlag;
    
    /**
    * 角色类型(0代表超级管理员  1其他)
    */
    private Integer roleType;
    
    /**
    * 创建人uid
    */
    private String createUserUid;
    
    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    
    /**
    * 修改人uid
    */
    private String editUserUid;
    
    /**
    * 修改时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date editTime;
    


}
