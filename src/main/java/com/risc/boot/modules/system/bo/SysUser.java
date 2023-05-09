package com.risc.boot.modules.system.bo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表(SysUser)实体类
 *
 * @author 李良发
 * @since 2023-05-08 10:59:18
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -90712342674311637L;
    
    /**
    * 用户uid
    */
    private String userUid;
    
    /**
    * 用户账号
    */
    private String userName;
    
    /**
    * 用户名称
    */
    private String userNick;
    
    /**
    * 密码
    */
    private String passWord;
    
    /**
    * 盐值
    */
    private String salt;
    
    /**
    * 公开uid
    */
    private String openUid;
    
    /**
    * 性别（0男1女）
    */
    private Integer sex;
    
    /**
    * 状态（-1删除、0激活、1禁用、2待验证）
    */
    private Integer userStatus;
    
    /**
    * 邮箱
    */
    private String email;
    
    /**
    * 头像
    */
    private String profilePicture;
    
    /**
    * 手机号
    */
    private String mobile;
    
    /**
    * 所在地区（省）
    */
    private String province;
    
    /**
    * 所在地区（市）
    */
    private String city;
    
    /**
    * 所在地区（区、县）
    */
    private String area;
    
    /**
    * 联系地址
    */
    private String address;
    
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
    private String updateUserUid;
    
    /**
    * 修改时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
    


}
