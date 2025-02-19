package com.trading.tcg.application.banner.port.out

import com.trading.tcg.application.banner.dto.request.FindBannersQuery
import com.trading.tcg.application.banner.dto.response.BannerDto
import com.trading.tcg.global.dto.Pageable

interface BannerPersistencePort {
    fun findBanners(query: FindBannersQuery): Pageable<List<BannerDto>>
}
