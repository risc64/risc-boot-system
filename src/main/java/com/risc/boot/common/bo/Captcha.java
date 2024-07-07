package com.risc.boot.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 验证码
 * @author liliangfa
 * @date 2024/4/29 15:31
 * @since V 1.0
 */
@Data
public class Captcha implements Serializable {
    
    private static final long serialVersionUID = -25501356554260013L;
    
    /**
     * 验证码缓存key
     */
    private String captchaKey;
    
    /**
     * 验证码图片base64
     */
    private String captchaBase64;
}
