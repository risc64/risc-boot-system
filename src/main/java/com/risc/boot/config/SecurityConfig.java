package com.risc.boot.config;

import com.risc.boot.modules.system.service.Impl.SecurityUserDeatailServiceImpl;
import com.risc.boot.security.JwtAuthenticationTokenFilter;
import com.risc.boot.security.SecurityAccessDeniedHandler;
import com.risc.boot.security.SecurityAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Spring Security配置类
 * @author 李良发
 * @version v1.0.0
 * @since 5/8/2023 11:04 AM
 */
//@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    /**
     * 自定义token认证失败处理
     */
    @Resource
    SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    /**
     * 自定义角色权限认证失败处理
     * @return
     */
    @Resource
    SecurityAccessDeniedHandler securityAccessDeniedHandler;
    
    ///**
    // * 自定义JWT登录授权过滤器
    // */
    //@Resource
    //JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    
    /**
     * 装载BCrypt密码编码器
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityUserDeatailServiceImpl userDetailsService() {
        return new SecurityUserDeatailServiceImpl();
    }
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated();
        // 基于token，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 异常处理 没有token或没有权限
        http.exceptionHandling()
                .accessDeniedHandler(securityAccessDeniedHandler)
                .authenticationEntryPoint(securityAuthenticationEntryPoint);
        
        return http.build();
        
        //ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        //
        ////不需要保护的资源路径允许访问
        //for (String url : ignoreUrlsConfig.getUrls()) {
        //    registry.antMatchers(url).permitAll();
        //}
        ////允许跨域请求的OPTIONS请求
        //registry.antMatchers(HttpMethod.OPTIONS)
        //        .permitAll();
        //http
        //        // 由于使用的是JWT，我们这里不需要csrf
        //        .csrf().disable()
        //        // 跨域
        //        .cors().and()
        //        // 基于token，所以不需要session
        //        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //        .and()
        //        .authorizeRequests()
        //        .anyRequest()// 除上面外的所有请求全部需要鉴权认证
        //        .authenticated();
        //// 禁用缓存
        //http.headers().cacheControl();
        //// 添加JWT filter
        //http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        ////添加自定义未授权和未登录结果返回
        //http.exceptionHandling()
        //        // 没有权限
        //        .accessDeniedHandler(securityAccessDeniedHandler)
        //        .authenticationEntryPoint(securityAuthenticationEntryPoint);
        //
        //return http.build();
    }
    
}
