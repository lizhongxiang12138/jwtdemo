package com.demo.jwtauth.module.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/10/30 11:38
 */
@Component
@ConfigurationProperties("security.url")
@Getter
@Setter
public class SecurityUrl {

    private String[] anonPaths;

}
