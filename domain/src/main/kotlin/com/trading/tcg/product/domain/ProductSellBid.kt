package com.trading.tcg.product.domain

import com.trading.tcg.global.domain.Base
import com.trading.tcg.user.domain.User
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product_sell_bid")
class ProductSellBid(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: ProductBidStatus,

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(name = "quantity", nullable = false)
    val quantity: Int,

    @Column(name = "stock", nullable = false)
    val stock: Int,

    @Column(name = "closed_time", nullable = false)
    val closedTime: LocalDateTime
): Base() {
}
