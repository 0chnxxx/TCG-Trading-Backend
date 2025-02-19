package com.trading.tcg.banner.domain

import com.trading.tcg.global.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "banner")
class Banner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "image", nullable = false)
    val image: String,

    @Column(name = "url")
    val url: String,

    @Column(name = "order", nullable = false)
    val order: Int
): BaseEntity()
