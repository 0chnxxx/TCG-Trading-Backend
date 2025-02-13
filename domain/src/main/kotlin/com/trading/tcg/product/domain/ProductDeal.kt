package com.trading.tcg.product.domain

import com.trading.tcg.global.domain.Base
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product_deal")
class ProductDeal(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_bid_id", nullable = false)
    val buyBid: ProductBuyBid,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_bid_id", nullable = false)
    val sellBid: ProductBuyBid,

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(name = "quantity", nullable = false)
    val quantity: Int
): Base() {
}
