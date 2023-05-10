package com.risc.boot.security;

import cn.hutool.json.JSONUtil;
import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.bo.Token;
import com.risc.boot.common.util.JwtTokenUtil;
import com.risc.boot.common.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销逻辑
 * @author 李良发
 * @version v1.0.0
 * @since 5/10/2023 10:11 AM
 */
@Component
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Resource
    RedisUtil redisUtil;
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result<Token> result = new Result<>();
        String token = request.getHeader("token");
        if (token != null) {
            String username = jwtTokenUtil.getUserNameFromToken(token);
            if (StringUtils.isNotBlank(username)) {
                redisUtil.remove(username);
            }
        }
        result.setStatusEnum(StatusEnum.OK, null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(result));
        response.getWriter().flush();
        response.getWriter().println();
    }
}
