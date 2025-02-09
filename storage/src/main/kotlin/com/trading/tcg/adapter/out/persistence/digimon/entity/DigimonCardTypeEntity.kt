package com.trading.tcg.adapter.out.persistence.digimon.entity

import jakarta.persistence.*

@Entity
@Table(name = "digimon_card_type")
class DigimonCardTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name", nullable = false)
    val name: String
)
