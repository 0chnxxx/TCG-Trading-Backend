package com.trading.tcg.adapter.out.persistence.product.entity

import com.trading.tcg.adapter.out.persistence.global.entity.BaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product_deal")
class ProductDealEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_bid_id", nullable = false)
    val buyBid: ProductBuyBidEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_bid_id", nullable = false)
    val sellBid: ProductBuyBidEntity,

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(name = "quantity", nullable = false)
    val quantity: Int
): BaseEntity() {
}
