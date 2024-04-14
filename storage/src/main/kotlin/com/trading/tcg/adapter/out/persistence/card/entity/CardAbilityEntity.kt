package com.trading.tcg.adapter.out.persistence.card.entity

import jakarta.persistence.*

@Entity
@Table(name = "card_ability")
data class CardAbilityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: CardEntity,

    @Column(name = "type", nullable = false)
    val type: String? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "value")
    val value: String? = null,

    @Column(name = "description", nullable = false)
    val description: String? = null) {
}
