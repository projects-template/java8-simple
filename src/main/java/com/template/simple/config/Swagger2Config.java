package com.template.simple.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
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
 * swagger 配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 指定swagger 代码处理
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(Predicates.or(RequestHandlerSelectors.basePackage("com.template.simple.controller")))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 说明应用信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("java8 simple api")
            .description("java8 simple api")
            .contact(new Contact("cz", "https://www.simple.com/", "api@simple.com"))
            .version("1.0.0-SNAPSHOT")
            .build();
    }

}
