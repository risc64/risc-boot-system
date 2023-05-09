package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.SysPermission;
import com.risc.boot.modules.system.bo.SysUser;
import com.risc.boot.modules.system.service.SysPermissionService;
import com.risc.boot.modules.system.service.SysUserService;
import com.risc.boot.common.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义用户权限信息
 * @author 李良发
 * @version v1.0.0
 * @since 5/8/2023 3:55 PM
 */
@Service
public class SecurityUserDeatailServiceImpl implements UserDetailsService {
    
    @Resource
    SysUserService sysUserService;
    
    @Resource
    SysPermissionService sysPermissionService;
    
    @Resource
    RedisUtil redisUtil;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.selectByUserName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 根据用户获取角色uid
        List<SysPermission> sysPermissionList = sysPermissionService.selectByUserUid(sysUser.getUserUid());
        List<String> codeList = sysPermissionList.stream().map(e-> e.getPermissionCode()).collect(Collectors.toList());
        List<GrantedAuthority> authorities = new ArrayList<>();
        redisUtil.set(username, StringUtils.join(codeList,","),8 * 60 * 60);
        sysPermissionList.forEach(sysPermission ->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
            authorities.add(simpleGrantedAuthority);
        });
        return new User(username, sysUser.getPassWord(), authorities);
    }
}
