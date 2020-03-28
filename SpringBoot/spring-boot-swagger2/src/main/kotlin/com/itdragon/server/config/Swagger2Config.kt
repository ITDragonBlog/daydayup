package com.itdragon.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class Swagger2Config {

    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)  // 指定生成的文档的类型是Swagger2
                .apiInfo(itDragonApiInfo())         // 配置文档页面的基本信息内容
                .select()
                // 指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.itdragon.server.api.rest"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun itDragonApiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("ITDragon Swagger API Document")
                .description("ITDragon Swagger Description...")
                .contact(Contact("ITDragon", "https://www.cnblogs.com/itdragon/", "itdragon@qq.com"))
                .version("0.1")
                .description("ITDragon SpringBoot Swagger API INFO")
                .build()
    }
}