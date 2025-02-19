package com.trading.tcg.application.banner.service

import com.trading.tcg.application.banner.dto.request.FindBannersQuery
import com.trading.tcg.application.banner.dto.response.BannerDto
import com.trading.tcg.application.banner.port.`in`.BannerUseCase
import com.trading.tcg.application.banner.port.out.BannerPersistencePort
import com.trading.tcg.global.dto.Pageable
import org.springframework.stereotype.Service

@Service
class BannerService(
    private val bannerPersistencePort: BannerPersistencePort
): BannerUseCase {
    override fun findBanners(query: FindBannersQuery): Pageable<List<BannerDto>> {
        return bannerPersistencePort.findBanners(query)
    }
}
