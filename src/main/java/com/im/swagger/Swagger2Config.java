package com.im.swagger;

import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 配置文件
 *
 * @author liubin
 * @since 2019-01-17 7:50:20
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class Swagger2Config {
    /**
     * 添加摘要信息(Docket)
     */
//    @Bean
//    public Docket controllerApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("IM即时通信_接口文档")
//                        .description("描述：用于管理项目各个接口")
//                        .contact(new Contact("Socks", null, null))
//                        .version("版本号:1.0")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.im.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.im.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(" RestApi Document")
                .description("decription for api")
                .termsOfServiceUrl("www.test.com")
                .contact("lytech")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("apikey", "Authorization", "header");
    }
}


