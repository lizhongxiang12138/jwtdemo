package com.demo.jwtauth.module.auth.service;

import com.demo.jwtauth.module.auth.dto.AuthDTO;
import com.demo.jwtauth.module.auth.dto.LoginDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * 描述: 认证业务接口
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/28 17:53
 */
public interface AuthService {

    /**
     * 登录
     * @return
     */
    AuthDTO login(LoginDTO loginDTO) throws Exception;
}
