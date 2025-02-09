package com.trading.tcg.adapter.out.persistence.product.entity

import com.trading.tcg.adapter.out.persistence.global.entity.BaseEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserProductBookmarkEntity
import com.trading.tcg.application.product.domain.Product
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "recent_price", precision = 10, scale = 2)
    val recentPrice: BigDecimal?,

    @Column(name = "deal_count", nullable = false)
    val dealCount: Int,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val deals: List<ProductDealEntity>,

    @Column(name = "direct_buy_price", precision = 10, scale = 2)
    val directBuyPrice: BigDecimal?,

    @Column(name = "buy_bid_count", nullable = false)
    val buyBidCount: Int,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val buyBids: List<ProductBuyBidEntity>,

    @Column(name = "direct_sell_price", precision = 10, scale = 2)
    val directSellPrice: BigDecimal?,

    @Column(name = "sell_bid_count", nullable = false)
    val sellBidCount: Int,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val sellBids: List<ProductSellBidEntity>,

    @Column(name = "bookmark_count", nullable = false)
    val bookmarkCount: Int,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val bookmarks: List<UserProductBookmarkEntity>
): BaseEntity() {
    abstract fun toDomain(): Product
}
