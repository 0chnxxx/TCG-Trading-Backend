package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.application.card.domain.YugiohCardPack
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "yugioh_card_pack")
class YugiohCardPackEntity(
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
) {
    fun toDomain(): YugiohCardPack {
        return YugiohCardPack(
            id = id,
            category = category,
            name = name,
            releaseDate = releaseDate
        )
    }
}
