package com.trading.tcg.adapter.out.persistence.product.entity

import com.trading.tcg.adapter.out.persistence.global.entity.BaseEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserEntity
import com.trading.tcg.application.product.domain.ProductDealType
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
    @JoinColumn(name = "buyer_id", nullable = false)
    val buyer: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    val seller: UserEntity,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: ProductDealType,

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(name = "quantity", nullable = false)
    val quantity: Int
): BaseEntity() {
}
