package com.trading.tcg.product.domain

import com.trading.tcg.global.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product")
@DiscriminatorColumn(name = "tab")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "code", nullable = false)
    val code: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "image", nullable = false)
    val image: String,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val deals: List<ProductDealBid>,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val buyBids: List<ProductBuyBid>,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val sellBids: List<ProductSellBid>,

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val bookmarks: List<ProductBookmark>,
): BaseEntity()
