package com.risc.boot.modules.system.bo;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 权限表(SysPermission)实体类
 *
 * @author 李良发
 * @since 2023-05-08 11:01:01
 */
@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 876339242419927499L;
    
    /**
    * 权限uid
    */
    private String uid;
    
    /**
    * 上一级权限uid
    */
    private String parentUid;
    
    /**
    * 权限名
    */
    private String permissionName;
    
    /**
    * 权限编码
    */
    private String permissionCode;
    
    /**
    * 权限类型（0菜单、1操作）
    */
    private Integer permissionType;
    
    /**
    * 请求方式(如GET,POST,PUT,DELETE)
    */
    private String requestMethod;
    
    /**
    * 菜单路径（前端路由）
    */
    private String menuUrl;
    
    /**
     * 前端页面
     */
    private String menuComponent;
    
    /**
    * 权限等级
    */
    private Integer permissionLevel;
    
    /**
    * 排序
    */
    private Integer sort;
    
    /**
    * 图标样式
    */
    private String iconStyle;
    
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
    
    
    //======一下为非表字段=====================
    
    /**
     * 子菜单
     */
    private List<SysPermission> childList;
    
    /**
     * 角色uid
     */
    private String roleUid;
}
