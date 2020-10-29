package com.demo.jwtsecurity.module.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * 描述: 登录DTO
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/28 17:49
 */
@Getter
@Setter
@ApiModel("登录数据传输模型")
public class LoginDTO {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

}
