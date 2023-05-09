package com.risc.boot.security;

import com.risc.boot.common.util.JwtTokenUtil;
import com.risc.boot.common.util.RedisUtil;
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
    
    private final static String JWT_TOKEN_HEADER = "token";

    private final static String JWT_TOKEN_HEAD = "Bearer";

    
    
    @Resource
    UserDetailsService userDetailsService;
    
    @Resource
    JwtTokenUtil jwtTokenUtil;
    
    @Resource
    RedisUtil redisUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JWT_TOKEN_HEADER);
        if (authHeader != null && authHeader.startsWith(JWT_TOKEN_HEAD)) {
            String authToken = authHeader.substring(JWT_TOKEN_HEAD.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            logger.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
