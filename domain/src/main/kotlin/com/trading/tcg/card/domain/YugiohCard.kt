package com.trading.tcg.card.domain

import com.trading.tcg.product.domain.*
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "yugioh_card")
@DiscriminatorValue("YUGIOH")
class YugiohCard(
    id: Long?,

    recentDealPrice: BigDecimal?,

    dealCount: Int,

    deals: List<ProductDealBid>,

    directBuyPrice: BigDecimal?,

    buyBidCount: Int,

    buyBids: List<ProductBuyBid>,

    directSellPrice: BigDecimal?,

    sellBidCount: Int,

    sellBids: List<ProductSellBid>,

    bookmarkCount: Int,

    bookmarks: List<ProductBookmark>,

    @ManyToMany
    @JoinTable(
        name = "yugioh_card_pack_catalog",
        joinColumns = [JoinColumn(name = "card_id")],
        inverseJoinColumns = [JoinColumn(name = "pack_id")]
    )
    val packs: List<YugiohCardPack>,

    @Column(name = "code", nullable = false)
    val code: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "image", nullable = false)
    val image: String,

    @Column(name = "categories", nullable = false)
    val categories: String,

    @Column(name = "type", nullable = false)
    val type: String,

    @Column(name = "effect")
    val effect: String?,

    @Column(name = "species")
    val species: String?,

    @Column(name = "summon_type")
    val summonType: String?,

    @Column(name = "summon_value")
    val summonValue: Int?,

    @Column(name = "marker")
    val marker: String?,

    @Column(name = "attack")
    val attack: String?,

    @Column(name = "defence")
    val defence: String?,

    @Column(name = "pendulum_scale")
    val pendulumScale: Int?,

    @Column(name = "pendulum_description")
    val pendulumDescription: String?,

    @Column(name = "description")
    val description: String?
): Product(
    id = id,
    recentDealPrice = recentDealPrice,
    dealCount = dealCount,
    deals = deals,
    directBuyPrice = directBuyPrice,
    buyBidCount = buyBidCount,
    buyBids = buyBids,
    directSellPrice = directSellPrice,
    sellBidCount = sellBidCount,
    sellBids = sellBids,
    bookmarkCount = bookmarkCount,
    bookmarks = bookmarks
)
