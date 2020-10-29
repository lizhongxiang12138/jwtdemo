package com.demo.jwtauth.module.security.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 描述:jwt 模型
 *
 * @Auther: lzx
 * @Date: 2019/7/9 13:42
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtModel {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户权限")
    private List<String> permissionList;

    @ApiModelProperty("用户当前令牌")
    private String myCurrentToken;

}
