package com.allstate.payments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(generateApiInfo())
                .groupName("public-api")
                .select()
                .paths( url -> url.startsWith("/api") )
                .build();
    }

    public ApiInfo generateApiInfo() {
        return new ApiInfoBuilder()
                .title("Payments API")
                .description("API for Credit Card Transaction Payments")
                .build();
    }

}
