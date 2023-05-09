package com.risc.boot.security;

import cn.hutool.json.JSONUtil;
import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义角色权限认证失败返回结果
 * @author 李良发
 * @version v1.0.0
 * @since 5/8/2023 2:24 PM
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> result = new Result<>();
        result.setStatusEnum(StatusEnum.PERMISSION_DENIED, null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(result));
        response.getWriter().flush();
    }
}
