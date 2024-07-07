package com.risc.boot.common.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限令牌
 * @author 李良发
 * @version 1.0.0
 * @date 2020/1/10 16:37
 * @since 1.0.0
 **/
@Data
public class Token implements Serializable {
    
    private static final long serialVersionUID = -25501356554260011L;

    /**
     * 组织uid
     */
    private String organizationUid;
    
    /**
     * 组织名
     */
    private String organizationName;

    /**
     * 组织编码
     */
    private String organizationCode;
    
    /**
     * 角色uid
     */
    private String roleUid;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 用户uid
     */
    private String userUid;

    /**
     * 登录账号
     */
    private String userName;
    
    /**
     * 用户名（内部真实姓名）
     */
    private String userNick;
    
    /**
     * 密码
     */
    private String passWord;
    
    /**
     * 是否记住
     */
    private Boolean remeberMe;
    
    /**
     * 令牌
     */
    private String jwtToken;

    /**
     * openid
     */
    private String openUid;

    /**
     * 微信小程序openid
     */
    private String wxMiniOpenId;

    /**
     * 微信用户unionid
     */
    private String wxUnionId;

    /**
     * 验证码缓存key
     */
    private String captchaKey;

    /**
     * 验证码
     */
    private String captchaCode;

    /**
     * 是否首次登录
     */
    private Boolean firstLogin;

    /**
     * 上次登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 验证码坐标集合
     */
    private List<Point> pointList;

    /**
     * 头像地址
     */
    private String profilePicture;
}
