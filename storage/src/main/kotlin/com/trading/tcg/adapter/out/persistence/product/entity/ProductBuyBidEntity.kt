package com.trading.tcg.adapter.out.persistence.product.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserEntity
import com.trading.tcg.application.product.domain.ProductBidStatus
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product_buy_bid")
class ProductBuyBidEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "product_id", nullable = false)
    val product: ProductEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id", nullable = false)
    val user: UserEntity,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: ProductBidStatus,

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(name = "quantity", nullable = false)
    val quantity: Int,

    @Column(name = "closed_time", nullable = false)
    val closedTime: LocalDateTime
): BaseEntity() {
}
