package com.trading.tcg.adapter.out.persistence.digimon.entity

import jakarta.persistence.*

@Entity
@Table(name = "digimon_card_type_catalog")
class DigimonCardTypeCatalogEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: DigimonCardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    val type: DigimonCardTypeEntity
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as DigimonCardTypeCatalogEntity

        return card == other.card && type == other.type
    }

    override fun hashCode(): Int {
        return 31 * card.hashCode() + type.hashCode()
    }
}
