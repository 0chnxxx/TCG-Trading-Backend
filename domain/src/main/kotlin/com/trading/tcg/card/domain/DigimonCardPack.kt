package com.trading.tcg.card.domain

import jakarta.persistence.*

@Entity
@Table(name = "digimon_card_pack")
class DigimonCardPack(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "code")
    val code: String?,

    @Column(name = "name", nullable = false)
    val name: String
)
