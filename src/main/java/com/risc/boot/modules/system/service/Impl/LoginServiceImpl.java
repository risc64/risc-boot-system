package com.risc.boot.modules.system.service.Impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.risc.boot.common.bo.*;
import com.risc.boot.common.constant.SystemConstants;
import com.risc.boot.common.enums.CaptchaTypeEnum;
import com.risc.boot.common.util.JwtTokenUtil;
import com.risc.boot.config.CaptchaConfig;
import com.risc.boot.modules.system.bo.SysPermission;
import com.risc.boot.modules.system.bo.SysUser;
import com.risc.boot.modules.system.service.LoginService;
import com.risc.boot.modules.system.service.SysPermissionService;
import com.risc.boot.modules.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.risc.boot.common.util.RedisUtil;
import com.risc.boot.common.util.ExceptionUtil;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录服务实现
 * @author 李良发
 * @version v1.0.0
 * @since 5/9/2023 9:15 AM
 */
@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Resource
    SysUserService sysUserService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    SysPermissionService sysPermissionService;

    @Resource
    RedisUtil redisUtil;
    
    @Resource
    JwtTokenUtil jwtTokenUtil;
    
    @Resource
    CaptchaConfig captchaConfig;
    
    @Resource
    private CodeGenerator codeGenerator;
    
    @Resource
    private Font captchaFont;
    
    @Override
    public Result<Token> login(Token record) {
        Result<Token> result = new Result<>();
        try {
//            System.out.println(passwordEncoder.encode(password));
            String captchaCode = record.getCaptchaCode();
            String captchaKey = record.getCaptchaKey();
            if (StringUtils.isBlank(captchaCode) || StringUtils.isBlank(captchaKey)) {
                result.setStatusEnum(StatusEnum.CAPTCHA_CODE_ERROR, null);
                return result;
            }
            String redisCaptchaCode = (String) redisUtil.get(SystemConstants.CAPTCHA_CODE_PREFIX + captchaKey);
            if (!codeGenerator.verify(redisCaptchaCode, captchaCode)) {
                result.setStatusEnum(StatusEnum.CAPTCHA_CODE_ERROR, null);
                return result;
            }
            Token t = sysUserService.selectTokenByUserName(record.getUserName());
            if (t == null) {
                result.setStatusEnum(StatusEnum.ERROR, null);
                result.setMsg("用户名或密码错误");
            } else if (!passwordEncoder.matches(record.getPassWord(), t.getPassWord())) {
                result.setStatusEnum(StatusEnum.ERROR, null);
                result.setMsg("用户名或密码错误");
            } else {
                List<SysPermission> sysPermissionList = sysPermissionService.selectByUserUid(t.getUserUid());
                if (sysPermissionList == null || sysPermissionList.size() == 0) {
                    result.setStatusEnum(StatusEnum.PERMISSION_DENIED, null);
                }
                List<String> codeList = sysPermissionList.stream().map(e -> e.getPermissionCode()).collect(Collectors.toList());
                SecurityUserDetails securityUserDetails = SecurityUserDetails.builder().username(t.getUserUid())
                        .password(t.getPassWord()).authorityList(codeList).build();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUserDetails, null, securityUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                String token = jwtTokenUtil.generateToken(securityUserDetails);
                if (StringUtils.isNotBlank(token)) {
                    t.setJwtToken(token);
                    t.setPassWord(null);
                    result.setStatusEnum(StatusEnum.OK, t);
                    redisUtil.set(t.getUserUid(), JSONUtil.toJsonPrettyStr(securityUserDetails), 8 * 60 * 60);
                } else {
                    result.setStatusEnum(StatusEnum.ERROR, null);
                    result.setMsg("token 生成失败");
                }
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }

    @Override
    public Result<Token> logout(String token) {
        Result<Token> result = new Result<>();
        try {
            String userUid = jwtTokenUtil.getUserNameFromToken(token);
            if (StringUtils.isNotBlank(userUid)) {
                redisUtil.del(userUid);
            }
            result.setStatusEnum(StatusEnum.OK, null);
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    @Override
    public Result<Captcha> captcha() {
        Result<Captcha> result = new Result<>();
        String captchaType = captchaConfig.getType();
        int width = captchaConfig.getWidth();
        int height = captchaConfig.getHeight();
        int interfereCount = captchaConfig.getInterfereCount();
        int codeLength = captchaConfig.getCode().getLength();
    
        AbstractCaptcha captcha;
        if (CaptchaTypeEnum.CIRCLE.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createCircleCaptcha(width, height, codeLength, interfereCount);
        } else if (CaptchaTypeEnum.GIF.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createGifCaptcha(width, height, codeLength);
        } else if (CaptchaTypeEnum.LINE.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createLineCaptcha(width, height, codeLength, interfereCount);
        } else if (CaptchaTypeEnum.SHEAR.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createShearCaptcha(width, height, codeLength, interfereCount);
        } else {
            throw new IllegalArgumentException("Invalid captcha type: " + captchaType);
        }
        captcha.setGenerator(codeGenerator);
        captcha.setTextAlpha(captchaConfig.getTextAlpha());
        captcha.setFont(captchaFont);
    
        String captchaCode = captcha.getCode();
        String imageBase64Data = captcha.getImageBase64Data();
        String captchaKey = IdUtil.fastSimpleUUID();
        redisUtil.set(SystemConstants.CAPTCHA_CODE_PREFIX + captchaKey, captchaCode, captchaConfig.getExpireSeconds());
        Captcha captcha1 = new Captcha();
        captcha1.setCaptchaKey(captchaKey);
        captcha1.setCaptchaBase64(imageBase64Data);
        result.setData(captcha1);
        return result;
    }
}
