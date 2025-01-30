package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_category")
class PokemonCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    val cards: List<PokemonCardCategoryEntity>,
) : BaseEntity() {
}
