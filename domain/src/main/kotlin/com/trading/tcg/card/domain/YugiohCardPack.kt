package com.trading.tcg.card.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "yugioh_card_pack")
class YugiohCardPack(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "category", nullable = false)
    val category: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "release_date", nullable = false)
    val releaseDate: LocalDate
)
