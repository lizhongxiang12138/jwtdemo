package com.demo.jwtauth.module.auth.dto;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 *
 * 描述: 认证结果DTO
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/28 17:50
 */
@Getter
@Setter
public class AuthDTO {

    private LoginDTO loginDTO;

    private String token;

}
