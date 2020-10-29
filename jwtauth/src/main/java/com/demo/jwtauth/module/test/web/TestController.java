package com.demo.jwtauth.module.test.web;

import com.demo.jwtsecurity.annotation.NeedApi;
import com.demo.jwtsecurity.module.auth.constant.PermissionConstant;
import com.demo.jwtsecurity.module.auth.utils.JwtUtil;
import com.demo.jwtauth.module.test.vo.ParseJWTVO;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 * 描述: 测试接口
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/29 10:12
 */
@RestController
@Api(tags = {"测试接口"})
@RequestMapping("/test")
@NeedApi
public class TestController {


    @ApiOperation("解析jwt令牌")
    @GetMapping("/parseJWT")
    public ParseJWTVO parseJWT() throws Exception {
        ParseJWTVO parseJWTVO = new ParseJWTVO();

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(token);

        Date expiration = claims.getExpiration();
        String subject = claims.getSubject();

        parseJWTVO.setSubject(subject);
        parseJWTVO.setExpiration(expiration);

        return parseJWTVO;
    }

    @ApiOperation("有test:add权限可以访问的接口")
    @GetMapping("/testAdd")
    @PreAuthorize("@el.check('"+ PermissionConstant.TEST_ADD +"')")
    public String testAdd(){
        return "有test:add权限";
    }

    @ApiOperation("有test:delete权限可以访问的接口")
    @GetMapping("testDelete")
    @PreAuthorize("@el.check('t"+PermissionConstant.TEST_DELETE+"')")
    public String testDelete(){
        return "有test:delete权限";
    }


}
