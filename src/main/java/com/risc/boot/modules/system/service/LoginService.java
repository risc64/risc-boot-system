package com.risc.boot.modules.system.service;

import com.risc.boot.common.bo.Captcha;
import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.Token;

/**
 * 登录服务
 * @author 李良发
 * @version v1.0.0
 * @since 5/9/2023 9:14 AM
 */
public interface LoginService {
    /**
     * 登录
     * @param record
     * @return
     */
    Result<Token> login(Token record);

    /**
     * 注销
     * @param token
     * @return
     */
    Result<Token> logout(String token);
    
    /**
     * 获取验证码
     * @return
     */
    Result<Captcha> captcha();
    
}
