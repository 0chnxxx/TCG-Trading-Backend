package com.trading.tcg.adapter.out.persistence.yugioh.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "yugioh_card_attribute_catalog")
@IdClass(YugiohCardAttributeCatalogEntity.YugiohCardAttributeCatalogId::class)
data class YugiohCardAttributeCatalogEntity(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: YugiohCardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    val attribute: YugiohCardAttributeEntity
) {
    @Embeddable
    data class YugiohCardAttributeCatalogId(
        val card: Long,
        val attribute: Long
    ): Serializable {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is YugiohCardAttributeCatalogId) return false

            return card == other.card && attribute == other.attribute
        }

        override fun hashCode(): Int {
            return 31 * card.hashCode() + attribute.hashCode()
        }
    }
}
