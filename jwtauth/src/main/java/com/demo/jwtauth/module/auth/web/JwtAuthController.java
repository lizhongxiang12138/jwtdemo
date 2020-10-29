package com.demo.jwtauth.module.auth.web;

import com.demo.jwtsecurity.annotation.AnonymousAccess;
import com.demo.jwtsecurity.annotation.NeedApi;
import com.demo.jwtsecurity.module.auth.dto.AuthDTO;
import com.demo.jwtsecurity.module.auth.dto.LoginDTO;
import com.demo.jwtsecurity.module.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 描述: jwt认证
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/29 09:17
 */
@RestController
@RequestMapping("/jwtAuth")
@NeedApi
@Api(tags = {"jwt认证"})
public class JwtAuthController {

    private AuthService authService;

    public JwtAuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 登录认证接口
     * 可以匿名访问
     * @param loginDTO
     * @return
     */
    @AnonymousAccess
    @PostMapping("/login")
    @ApiOperation("登录获取token")
    public AuthDTO login(@RequestBody LoginDTO loginDTO) throws Exception {
        return authService.login(loginDTO);
    }

}
