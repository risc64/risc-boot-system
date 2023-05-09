package com.risc.boot.security;

import cn.hutool.json.JSONUtil;
import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义token认证失败处理
 * @author 李良发
 * @version v1.0.0
 * @since 5/8/2023 2:22 PM
 */
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> result = new Result<>();
        result.setStatusEnum(StatusEnum.OVERDUEL, null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(result));
        response.getWriter().flush();
        response.getWriter().println();
    }
}
