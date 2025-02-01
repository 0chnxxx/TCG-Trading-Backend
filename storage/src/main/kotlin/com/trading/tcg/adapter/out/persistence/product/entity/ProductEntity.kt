package com.trading.tcg.adapter.out.persistence.product.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import com.trading.tcg.application.product.domain.ProductCategory
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    val category: ProductCategory,

    @Column(name = "recent_price", precision = 10, scale = 2)
    val recentPrice: BigDecimal,

    @Column(name = "direct_buy_price", precision = 10, scale = 2)
    val directBuyPrice: BigDecimal,

    @Column(name = "direct_sell_price", precision = 10, scale = 2)
    val directSellPrice: BigDecimal,

    @Column(name = "deal_count")
    val dealCount: Int,

    @Column(name = "buy_bid_count")
    val buyBidCount: Int,

    @Column(name = "sell_bid_count")
    val sellBidCount: Int
): BaseEntity() {
}
