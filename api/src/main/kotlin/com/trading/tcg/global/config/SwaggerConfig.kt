package com.trading.tcg.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .servers(listOf(Server().url("http://localhost:8080").description("로컬 서버")))
            .components(
                Components()
                    .addSecuritySchemes("Authorization", SecurityScheme().type(SecurityScheme.Type.APIKEY).name("Authorization").`in`(SecurityScheme.In.HEADER))
            )
            .security(listOf(SecurityRequirement().addList("Authorization")))
            .info(
                Info()
                    .title("TCG-Trading API")
                    .description("TCG-Trading System의 API 명세서입니다.")
                    .version("v0.0.1")
            )
    }
}
