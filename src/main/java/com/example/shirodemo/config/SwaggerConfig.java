package com.example.shirodemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2API文档的配置
 * 访问地址
 * http://localhost:端口号/swagger-ui/
 * 我搭建项目喜欢使用springboot默认端口8080
 * http://localhost:8080/swagger-ui/
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                /**
                 * 重点说明:
                 * 其余都是可以默认,但是controller扫描的路径一定要该队,是该项目的controller包路径
                 */
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                /**
                 * 指定项目的名称和主题
                 */
                .title("mybatis-plus演示")
                /**
                 * 描述项目的用途
                 */
                .description("复习swagger+mybatis-plus")
                /**
                 * name:使用者的姓名
                 * url:使用者的相关技术文章
                 * email:使用者的邮箱地址
                 */
                .contact(new Contact("shaoming", "http://www.shaoming.club", "1025378286@qq.com"))
                .version("1.0")
                .build();
    }
}
