package com.trading.tcg.card.domain

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "pokemon_card_pack_catalog")
@IdClass(PokemonCardPackCatalog.PokemonCardPackCatalogId::class)
class PokemonCardPackCatalog(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: PokemonCard,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    val pack: PokemonCardPack
) {
    @Embeddable
    data class PokemonCardPackCatalogId(
        val card: Long,
        val pack: Long
    ): Serializable {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PokemonCardPackCatalogId) return false

            return card == other.card && pack == other.pack
        }

        override fun hashCode(): Int {
            return 31 * card.hashCode() + pack.hashCode()
        }
    }
}
