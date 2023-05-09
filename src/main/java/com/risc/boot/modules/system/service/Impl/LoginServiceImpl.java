package com.risc.boot.modules.system.service.Impl;

import cn.hutool.json.JSONUtil;
import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.SecurityUserDetails;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.bo.Token;
import com.risc.boot.common.util.JwtTokenUtil;
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
    
    @Override
    public Result<Token> login(String userName, String password) {
        Result<Token> result = new Result<>();
        try {
            Token t = sysUserService.selectTokenByUserName(userName);
            if (t == null) {
                result.setStatusEnum(StatusEnum.ERROR, null);
                result.setMsg("用户名或密码错误");
            } else if (!passwordEncoder.matches(password, t.getPassWord())) {
                result.setStatusEnum(StatusEnum.ERROR, null);
                result.setMsg("用户名或密码错误");
            } else {
                List<SysPermission> sysPermissionList = sysPermissionService.selectByUserUid(t.getUserUid());
                List<String> codeList = sysPermissionList.stream().map(e-> e.getPermissionCode()).collect(Collectors.toList());
                //redisUtil.set(t.getUserName(), StringUtils.join(codeList,","),8 * 60 * 60);
                //List<GrantedAuthority> authorities = new ArrayList<>();
                //sysPermissionList.forEach(sysPermission ->{
                //    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                //    authorities.add(simpleGrantedAuthority);
                //});
                SecurityUserDetails securityUserDetails = SecurityUserDetails.builder().username(t.getUserName())
                        .password(t.getPassWord()).authorityList(codeList).build();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUserDetails, null, securityUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                String token = jwtTokenUtil.generateToken(securityUserDetails);
                if (StringUtils.isNotBlank(token)) {
                    t.setToken(token);
                    result.setStatusEnum(StatusEnum.OK, t);
                    redisUtil.set(t.getUserName(), JSONUtil.toJsonPrettyStr(securityUserDetails),8 * 60 * 60);
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
}
