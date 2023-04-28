package com.liu.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liushuaibiao
 * @date 2023/4/28 14:39
 * swagger配置
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    //配置swagger2
    public Docket createResApi() {

        return new Docket(DocumentationType.SWAGGER_2). //指定swagger版本
                apiInfo(apiInfo())//用于定义api文档总信息
                .select().apis(RequestHandlerSelectors.basePackage("com.liu.controller")) //指定扫描的包
                .paths(PathSelectors.any()).build(); //any 表示扫描所有
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().
                title("文档标题")           //文档页标题
                .contact(new Contact("liu", "i.misteryliu.top", "632755976@qq.com"))
                .description("api文档")
                .version("1.0.0")
                .termsOfServiceUrl("http://misteryliu.top").build();
    }


}
