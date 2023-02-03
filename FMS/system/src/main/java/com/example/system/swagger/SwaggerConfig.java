package com.example.system.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2//开启swagger
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String active;//yml中的环境，pro，dev等

    //配置swagger文档信息
    @Bean
    public Docket docket() {
        boolean flag = !active.equals("pro");//标志，active为dev则启动，为pro则关闭
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)//是否启用swagger，在生产环境下关闭
                .select()
                //RequestHandlerSelectors,配置要扫描的接口的方式
                //basePackage是包扫描，填web层的包
                //any扫描全部
                //none都不扫描
                //withClassAnnotation:根据注解扫描,参数是一个注解的class对象
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.example.system.controller"))
                //paths(PathSelectors.ant("/order/*"))过滤路径，扫描指定路径下的
                //none：不过滤
                //any：都过滤
                .build();
    }

    //配置swagger介绍信息apiInfo
    private ApiInfo apiInfo() {
        //作者信息，包含姓名，url，邮箱
        Contact contact = new Contact("徐鑫", "url", "email");
        return new ApiInfo(
                "标题",//标题
                "描述",//描述
                "版本",//版本
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
