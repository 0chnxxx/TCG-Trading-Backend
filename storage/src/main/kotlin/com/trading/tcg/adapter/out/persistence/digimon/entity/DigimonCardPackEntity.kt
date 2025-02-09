package com.trading.tcg.adapter.out.persistence.digimon.entity

import com.trading.tcg.application.card.domain.DigimonCardPack
import jakarta.persistence.*

@Entity
@Table(name = "digimon_card_pack")
class DigimonCardPackEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "code")
    val code: String?,

    @Column(name = "name", nullable = false)
    val name: String
) {
    fun toDomain(): DigimonCardPack {
        return DigimonCardPack(
            id = id,
            code = code,
            name = name
        )
    }
}
