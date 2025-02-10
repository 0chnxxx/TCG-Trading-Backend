package com.trading.tcg.adapter.out.persistence.card.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "yugioh_card_pack_catalog")
@IdClass(YugiohCardPackCatalogEntity.YugiohCardPackCatalogId::class)
class YugiohCardPackCatalogEntity(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: YugiohCardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    val pack: YugiohCardPackEntity
) {
    @Embeddable
    data class YugiohCardPackCatalogId(
        val card: Long,
        val pack: Long
    ): Serializable {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is YugiohCardPackCatalogId) return false

            return card == other.card && pack == other.pack
        }

        override fun hashCode(): Int {
            return 31 * card.hashCode() + pack.hashCode()
        }
    }
}
