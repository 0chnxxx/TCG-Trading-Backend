package com.trading.tcg.product.domain

import com.trading.tcg.global.domain.Base
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val deals: List<ProductDeal>,

    @Column(name = "recent_deal_price", precision = 10, scale = 2)
    val recentDealPrice: BigDecimal?,

    @Column(name = "deal_count", nullable = false)
    val dealCount: Int,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val buyBids: List<ProductBuyBid>,

    @Column(name = "direct_buy_price", precision = 10, scale = 2)
    val directBuyPrice: BigDecimal?,

    @Column(name = "buy_bid_count", nullable = false)
    val buyBidCount: Int,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val sellBids: List<ProductSellBid>,

    @Column(name = "direct_sell_price", precision = 10, scale = 2)
    val directSellPrice: BigDecimal?,

    @Column(name = "sell_bid_count", nullable = false)
    val sellBidCount: Int,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val bookmarks: List<ProductBookmark>,

    @Column(name = "bookmark_count", nullable = false)
    val bookmarkCount: Int
): Base()
