package com.trading.tcg.adapter.out.persistence.yugioh.entity

import jakarta.persistence.*

@Entity
@Table(name = "yugioh_card_attribute")
class YugiohCardAttributeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name", nullable = false)
    val name: String
)
