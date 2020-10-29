package com.demo.jwtsecurity.module.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Data
public class ApiError{

    private Integer status = 400;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String serviceCode= ServiceCode.showMsg.getCode();
    private String title;

    @Getter
    @AllArgsConstructor
    public enum ServiceCode{
        needLogin("10000","需要登录"),
        needRealName("10001","你没有实名认证，需要进行实名认证"),
        showMsg("10003","展示message"),
        needPayPassword("10004","需要支付密码"),
        alertMessage("10005","展示message"),
        authSuccessAlertMessageAndGoToPayPassword("10006","认证成功，alert，并跳转支付密码");
        private String code;
        private String desc;
    }

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setTitle("提示");
        return apiError;
    }

    public static ApiError error(Integer status, String message){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        apiError.setTitle("提示");
        return apiError;
    }

    public static ApiError error(Integer status, String message,String serviceCode){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        if(StringUtils.isNotBlank(serviceCode)){
            apiError.setServiceCode(serviceCode);
        }
        apiError.setTitle("提示");
        return apiError;
    }

    public static ApiError error(Integer status, String message,String serviceCode,String title){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        apiError.setTitle(title);
        if(StringUtils.isNotBlank(serviceCode)){
            apiError.setServiceCode(serviceCode);
        }
        return apiError;
    }
}


