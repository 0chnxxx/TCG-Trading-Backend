package com.trading.tcg.adapter.out.persistence.card.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "pokemon_card_pack_catalog")
@IdClass(PokemonCardPackCatalogEntity.PokemonCardPackCatalogId::class)
class PokemonCardPackCatalogEntity(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: PokemonCardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    val pack: PokemonCardPackEntity
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
