package com.demo.jwtauth.module.security.config;

import com.demo.jwtauth.module.security.model.JwtModel;
import com.demo.jwtauth.module.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 描述: 验证权限
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/29 14:00
 */
@Service(value = "el")
public class ElPermissionConfig {

    public Boolean check(String ...permissions){
        // 如果是匿名访问的，就放行
        String anonymous = "anonymous";
        if(Arrays.asList(permissions).contains(anonymous)){
            return true;
        }
        // 获取当前用户的所有权限
        List<String> elPermissions =  getJwtModel().getPermissionList();
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return elPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }

    public static JwtModel getJwtModel() {
        JwtModel jwtModel;
        try {
            jwtModel = (JwtModel) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        return jwtModel;
    }
}
