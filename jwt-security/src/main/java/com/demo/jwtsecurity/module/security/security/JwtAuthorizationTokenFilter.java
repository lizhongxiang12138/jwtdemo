package com.demo.jwtsecurity.module.security.security;

import com.demo.jwtsecurity.module.auth.utils.JwtUtil;
import com.demo.jwtsecurity.module.security.model.JwtModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * 描述: 认证过滤器
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/29 14:06
 */
@Slf4j
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private ObjectMapper objectMapper;

    public JwtAuthorizationTokenFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String jwtToken = request.getHeader("token");
        Claims claims = null;
        String subject = null;
        JwtModel jwtModel = null;

        try {
            //解析jwtToken
            claims =  JwtUtil.parseJWT(jwtToken);
            subject =  claims.getSubject();
            jwtModel = objectMapper.readValue(subject, new TypeReference<JwtModel>() {
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (jwtModel != null && claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for(String authoritiesStr : jwtModel.getPermissionList()){
                authorities.add(()->{return authoritiesStr;});
            }
            if(authorities.size()<=0){
                authorities.add(()->{return "new";});
            }

            //设置用户的当前令牌
            jwtModel.setMyCurrentToken(subject);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtModel, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
