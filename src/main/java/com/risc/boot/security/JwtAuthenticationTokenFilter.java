package com.risc.boot.security;

import cn.hutool.json.JSONUtil;
import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.SecurityUserDetails;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.util.JwtTokenUtil;
import com.risc.boot.common.util.RedisUtil;
import com.risc.boot.modules.system.service.Impl.SecurityUserDeatailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义JWT登录授权过滤器
 * @author 李良发
 * @version v1.0.0
 * @since 5/8/2023 2:29 PM
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String JWT_TOKEN_HEAD = "Bearer";
    
    @Resource
    JwtTokenUtil jwtTokenUtil;
    
    @Resource
    RedisUtil redisUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        filterChain.doFilter(request, response);
        // 获取token
        //String token = request.getHeader("token");
        //if (token != null && token.startsWith(JWT_TOKEN_HEAD)) {
        //    String authToken = token.substring(JWT_TOKEN_HEAD.length());
        //    String username = jwtTokenUtil.getUserNameFromToken(authToken);
        //    Object obj = redisUtil.get(username);
        //    if (obj != null) {
        //        String jsonStr = JSONUtil.toJsonPrettyStr(obj);
        //        SecurityUserDetails userDetails = JSONUtil.toBean(jsonStr, SecurityUserDetails.class);
        //        if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        //            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //            logger.info("authenticated user:{}", username);
        //            SecurityContextHolder.getContext().setAuthentication(authentication);
        //            filterChain.doFilter(request, response);
        //        } else {
        //            Result<String> result = new Result<>();
        //            result.setStatusEnum(StatusEnum.PERMISSION_DENIED, null);
        //            response.setCharacterEncoding("UTF-8");
        //            response.setContentType("application/json");
        //            response.getWriter().println(JSONUtil.parse(result));
        //            response.getWriter().flush();
        //        }
        //    } else {
        //        Result<String> result = new Result<>();
        //        result.setStatusEnum(StatusEnum.OVERDUEL, null);
        //        response.setCharacterEncoding("UTF-8");
        //        response.setContentType("application/json");
        //        response.getWriter().println(JSONUtil.parse(result));
        //        response.getWriter().flush();
        //        response.getWriter().println();
        //    }
        //} else {
        //    Result<String> result = new Result<>();
        //    result.setStatusEnum(StatusEnum.OVERDUEL, null);
        //    response.setCharacterEncoding("UTF-8");
        //    response.setContentType("application/json");
        //    response.getWriter().println(JSONUtil.parse(result));
        //    response.getWriter().flush();
        //    response.getWriter().println();
        //}
    }
}
