package com.demo.jwtsecurity.module.auth.service.impl;

import com.demo.jwtsecurity.module.auth.constant.PermissionConstant;
import com.demo.jwtsecurity.module.auth.dto.AuthDTO;
import com.demo.jwtsecurity.module.auth.dto.LoginDTO;
import com.demo.jwtsecurity.module.auth.service.AuthService;
import com.demo.jwtsecurity.module.auth.utils.JwtUtil;
import com.demo.jwtsecurity.module.security.model.JwtModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 *
 * 描述: 认证业务实现
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/28 17:55
 */
@Service
public class AuthServiceImpl implements AuthService {


    private ObjectMapper objectMapper;

    @Value("${org.my.jwt.effective-time}")
    private String effectiveTime;

    public AuthServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * jwt认证接口实现
     * @param loginDTO
     * @return
     */
    @Override
    public AuthDTO login(LoginDTO loginDTO) throws Exception {
        AuthDTO authDTO = new AuthDTO();

        ArrayList<String> roleIdList = new ArrayList<>(1);
        roleIdList.add(PermissionConstant.TEST_ADD);
        JwtModel jwtModel = new JwtModel(loginDTO.getUserName(), roleIdList,"");
        int effectivTimeInt = Integer.valueOf(effectiveTime.substring(0,effectiveTime.length()-1));
        String effectivTimeUnit = effectiveTime.substring(effectiveTime.length()-1,effectiveTime.length());
        String jwt = null;
        switch (effectivTimeUnit){
            case "s" :{
                //秒
                jwt = JwtUtil.createJWT(loginDTO.getUserName(), loginDTO.getUserName(), objectMapper.writeValueAsString(jwtModel), effectivTimeInt * 1000L);
                break;
            }
            case "m" :{
                //分钟
                jwt = JwtUtil.createJWT(loginDTO.getUserName(), loginDTO.getUserName(), objectMapper.writeValueAsString(jwtModel), effectivTimeInt * 60L * 1000L);
                break;
            }
            case "h" :{
                //小时
                jwt = JwtUtil.createJWT(loginDTO.getUserName(), loginDTO.getUserName(), objectMapper.writeValueAsString(jwtModel), effectivTimeInt * 60L * 60L * 1000L);
                break;
            }
            case "d" :{
                //小时
                jwt = JwtUtil.createJWT(loginDTO.getUserName(), loginDTO.getUserName(), objectMapper.writeValueAsString(jwtModel), effectivTimeInt * 24L * 60L * 60L * 1000L);
                break;
            }
        }

        loginDTO.setPassword("***");
        authDTO.setLoginDTO(loginDTO);
        authDTO.setToken(jwt);

        return authDTO;
    }
}
