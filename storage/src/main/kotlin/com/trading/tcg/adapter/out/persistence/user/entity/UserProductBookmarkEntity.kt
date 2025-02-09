package com.trading.tcg.adapter.out.persistence.user.entity

import com.trading.tcg.adapter.out.persistence.product.entity.ProductEntity
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "user_product_bookmark")
@IdClass(UserProductBookmarkEntity.UserProductBookmarkId::class)
class UserProductBookmarkEntity(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity
) {
    @Embeddable
    data class UserProductBookmarkId(
        val user: Long,
        val product: Long
    ): Serializable {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is UserProductBookmarkId) return false

            return user == other.user && product == other.product
        }

        override fun hashCode(): Int {
            return 31 * user.hashCode() + product.hashCode()
        }
    }
}
