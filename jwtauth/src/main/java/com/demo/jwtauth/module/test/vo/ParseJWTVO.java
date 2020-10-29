package com.demo.jwtauth.module.test.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("jwtToken解析VO")
@Getter
@Setter
public class ParseJWTVO {

    @ApiModelProperty("token解析后的主体")
    private String subject;

    @ApiModelProperty("token过期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date expiration;

}
