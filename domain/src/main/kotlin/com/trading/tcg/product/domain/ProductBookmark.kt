package com.trading.tcg.product.domain

import com.trading.tcg.user.domain.User
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "product_bookmark")
@IdClass(ProductBookmark.ProductBookmarkId::class)
class ProductBookmark(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
) {
    @Embeddable
    data class ProductBookmarkId(
        val user: Long,
        val product: Long
    ): Serializable {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ProductBookmarkId) return false

            return user == other.user && product == other.product
        }

        override fun hashCode(): Int {
            return 31 * user.hashCode() + product.hashCode()
        }
    }
}
