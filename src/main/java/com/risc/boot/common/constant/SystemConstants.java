package com.risc.boot.common.constant;

/**
 * 缓存常量
 *
 * @author haoxr
 * @since 2023/11/24
 */
public interface SystemConstants {

    /**
     * 验证码缓存前缀
     */
    String CAPTCHA_CODE_PREFIX = "captcha_code:";

    /**
     * 角色和权限缓存前缀
     */
    String ROLE_PERMS_PREFIX = "role_perms:";

    /**
     * 黑名单Token缓存前缀
     */
    String BLACKLIST_TOKEN_PREFIX = "token:blacklist:";


    /**
     * 登录路径
     */
    String LOGIN_PATH = "/auth/login";


    /**
     * JWT Token 前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";
    
    /**
     * 文件根路径
     */
    String FILE_ROOT_PATH = "/static";
    
    /**
     * 头像路径
     */
    String PROFILE_PICTURE_PATH = "/profilePicture";

}
