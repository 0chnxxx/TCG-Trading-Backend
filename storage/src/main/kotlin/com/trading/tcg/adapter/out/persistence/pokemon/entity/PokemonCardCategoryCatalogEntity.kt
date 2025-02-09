package com.trading.tcg.adapter.out.persistence.pokemon.entity

import com.trading.tcg.adapter.out.persistence.user.entity.UserProductBookmarkEntity.UserProductBookmarkId
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "pokemon_card_category_catalog")
@IdClass(PokemonCardCategoryCatalogEntity.PokemonCardCategoryCatalogId::class)
class PokemonCardCategoryCatalogEntity(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: PokemonCardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: PokemonCardCategoryEntity
) {
    @Embeddable
    data class PokemonCardCategoryCatalogId(
        val card: Long,
        val category: Long
    ): Serializable {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PokemonCardCategoryCatalogId) return false

            return card == other.card && category == other.category
        }

        override fun hashCode(): Int {
            return 31 * card.hashCode() + category.hashCode()
        }
    }
}
