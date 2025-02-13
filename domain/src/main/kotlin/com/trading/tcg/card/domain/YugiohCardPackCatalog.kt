package com.trading.tcg.card.domain

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "yugioh_card_pack_catalog")
@IdClass(YugiohCardPackCatalog.YugiohCardPackCatalogId::class)
class YugiohCardPackCatalog(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: YugiohCard,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    val pack: YugiohCardPack
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
