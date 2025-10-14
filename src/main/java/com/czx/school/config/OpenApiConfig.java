package com.czx.school.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    // 全局 API 文档信息配置（影响 Swagger UI 顶部展示）
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // API 基础信息：标题、描述、版本
                .info(new Info()
                        .title("🚀 栓子项目 API 文档")
                        .version("1.0.0")
                        .description("这是项目的 RESTful API 接口文档，包含学生、课程、选课等模块。")
                        .contact(new Contact()
                                .name("开发团队")
                                .email("1373833569@qq.com")
                                .url("https://1373833569@qq.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")))

                // 安全认证（比如 Bearer Token / JWT）
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))

                // 可选：配置服务地址，比如本地、测试、生产环境
                .addServersItem(new Server().url("http://localhost:8080").description("本地开发环境"));
                //.addServersItem(new Server().url("https://api.example.com").description("线上环境"));
    }
}
