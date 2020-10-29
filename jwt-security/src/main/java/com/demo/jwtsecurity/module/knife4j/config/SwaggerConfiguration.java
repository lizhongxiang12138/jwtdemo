package com.demo.jwtsecurity.module.knife4j.config;

import com.demo.jwtsecurity.annotation.NeedApi;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 描述: knife4j配置
 *
 * 作者: lizhongxiang
 *
 * 时间: 2020/10/29 09:07
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {


    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("token").description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("")
                .required(false)
                .build();
        pars.add(ticketPar.build());
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withClassAnnotation(NeedApi.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
        return docket;
    }

    /**
     * api接口信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui-demo RESTful APIs")
                .description("# swagger-bootstrap-ui-demo RESTful APIs")
                .termsOfServiceUrl("http://www.xx.com/")
                .contact("xx@qq.com")
                .version("1.0")
                .build();
    }

}
