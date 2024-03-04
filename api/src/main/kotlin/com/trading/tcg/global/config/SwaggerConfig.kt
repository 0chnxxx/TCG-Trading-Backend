package com.trading.tcg.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .servers(listOf(Server().url("http://localhost:8080").description("로컬 서버")))
            .components(Components())
            .info(Info()
                .title("TCG-Trading API")
                .description("TCG-Trading System의 API 명세서입니다.")
                .version("v0.0.1")
            )
    }
}
