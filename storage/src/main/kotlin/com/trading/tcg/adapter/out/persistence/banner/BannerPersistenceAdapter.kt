package com.trading.tcg.adapter.out.persistence.banner

import com.querydsl.jpa.impl.JPAQueryFactory
import com.trading.tcg.application.banner.dto.request.FindBannersQuery
import com.trading.tcg.application.banner.dto.response.BannerDto
import com.trading.tcg.application.banner.port.out.BannerPersistencePort
import com.trading.tcg.banner.domain.QBanner
import com.trading.tcg.global.dto.Pageable
import org.springframework.stereotype.Repository

@Repository
class BannerPersistenceAdapter(
    private val jpaQueryFactory: JPAQueryFactory
): BannerPersistencePort {
    override fun findBanners(query: FindBannersQuery): Pageable<List<BannerDto>> {
        val qBanner = QBanner.banner

        val totalCount = jpaQueryFactory
            .select(qBanner.id.countDistinct())
            .from(qBanner)
            .fetchOne() ?: 0

        val banners = jpaQueryFactory
            .select(qBanner)
            .from(qBanner)
            .orderBy(qBanner.order.asc())
            .offset(((query.page - 1) * query.size).toLong())
            .limit(query.size.toLong())
            .fetch()

        return Pageable(
            pageResult = Pageable.PageResult.of(
                totalCount = totalCount,
                page = query.page,
                size = query.size
            ),
            data = banners.map {
                BannerDto(
                    id = it.id!!,
                    image = it.image,
                    url = it.url
                )
            }
        )
    }
}
