package com.demo.jwtauth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 描述: 用于标记匿名访问方法
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/29 11:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
