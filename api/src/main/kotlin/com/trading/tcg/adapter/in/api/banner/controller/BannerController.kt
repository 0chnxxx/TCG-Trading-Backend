package com.trading.tcg.adapter.`in`.api.banner.controller

import com.trading.tcg.adapter.`in`.api.banner.dto.FindBannersRequest
import com.trading.tcg.adapter.`in`.swagger.banner.BannerSwagger
import com.trading.tcg.application.banner.dto.response.BannerDto
import com.trading.tcg.application.banner.port.`in`.BannerUseCase
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BannerController(
    private val bannerUseCase: BannerUseCase
): BannerSwagger {
    @GetMapping("/banners")
    override fun findBanners(
        @AuthenticationPrincipal
        provider: Provider,
        request: FindBannersRequest
    ): ResponseEntity<Response<List<BannerDto>>> {
        val banners = bannerUseCase.findBanners(request.toQuery())

        val response = Response.of(
            pageResult = banners.pageResult,
            data = banners.data
        )

        return ResponseEntity(response, HttpStatus.OK)
    }
}
