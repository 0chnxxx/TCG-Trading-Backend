package com.trading.tcg.adapter.`in`.swagger.banner

import com.trading.tcg.adapter.`in`.api.banner.dto.FindBannersRequest
import com.trading.tcg.application.banner.dto.response.BannerDto
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "배너")
interface BannerSwagger {
    @Operation(summary = "배너 목록 조회", description = "배너 목록을 조회한다.")
    fun findBanners(
        provider: Provider,
        request: FindBannersRequest
    ): ResponseEntity<Response<List<BannerDto>>>
}
