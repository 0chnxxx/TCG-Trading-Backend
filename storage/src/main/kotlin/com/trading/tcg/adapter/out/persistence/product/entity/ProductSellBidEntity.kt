package com.trading.tcg.adapter.out.persistence.product.entity

import com.trading.tcg.adapter.out.persistence.global.entity.BaseEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserEntity
import com.trading.tcg.application.product.domain.ProductBidStatus
import com.trading.tcg.application.product.domain.ProductBidType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product_sell_bid")
class ProductSellBidEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: ProductBidStatus,

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(name = "total_quantity", nullable = false)
    val totalQuantity: Int,

    @Column(name = "remaining_quantity", nullable = false)
    val remainingQuantity: Int,

    @Column(name = "closed_time", nullable = false)
    val closedTime: LocalDateTime
): BaseEntity() {
}
