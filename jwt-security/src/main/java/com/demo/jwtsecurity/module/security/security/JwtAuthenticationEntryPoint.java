package com.demo.jwtsecurity.module.security.security;

import com.demo.jwtsecurity.module.exception.handler.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    private ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiError apiError = ApiError.error(HttpServletResponse.SC_UNAUTHORIZED,"你的认证信息过期，需要从新登录",ApiError.ServiceCode.needLogin.getCode(),"提示");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(objectMapper.writeValueAsString(apiError));
//            log.debug("返回是\n");
//            log.debug(objectMapper.writeValueAsString(apiError));
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException==null?"Unauthorized":authException.getMessage());
    }
}
