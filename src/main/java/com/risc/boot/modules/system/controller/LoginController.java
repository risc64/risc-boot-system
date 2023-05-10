package com.risc.boot.modules.system.controller;

import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.bo.Token;
import com.risc.boot.modules.system.service.LoginService;
import com.risc.boot.common.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录逻辑
 * @author 李良发
 * @version v1.0.0
 * @since 5/9/2023 9:02 AM
 */
@RestController
public class LoginController {
    
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Resource
    LoginService loginService;
    
    

    @PostMapping(value = "login", produces = "application/json;charset=UTF-8")
    public Result<Token> login(@RequestBody Token record) {
        Result<Token> result = new Result<>();
        try {
            result = loginService.login(record.getUserName(), record.getPassWord());
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }

    @PostMapping(value = "logout", produces = "application/json;charset=UTF-8")
    public Result<Token> logout(HttpServletRequest request) {
        Result<Token> result = new Result<>();
        try {
            String token = request.getHeader("token");
            if (StringUtils.isNotBlank(token)) {
                result = loginService.logout(token);
            } else {
                result.setStatusEnum(StatusEnum.OVERDUEL, null);
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
}
