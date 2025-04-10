package com.trading.tcg.card.domain

import com.trading.tcg.product.domain.*
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "yugioh_card")
@DiscriminatorValue("YUGIOH")
class YugiohCard(
    id: Long?,
    code: String,
    name: String,
    image: String,
    deals: List<ProductDealBid>,
    buyBids: List<ProductBuyBid>,
    sellBids: List<ProductSellBid>,
    bookmarks: List<ProductBookmark>,

    @ManyToMany
    @JoinTable(
        name = "yugioh_card_pack_catalog",
        joinColumns = [JoinColumn(name = "card_id")],
        inverseJoinColumns = [JoinColumn(name = "pack_id")]
    )
    val packs: List<YugiohCardPack>,

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
    code = code,
    name = name,
    image = image,
    deals = deals,
    buyBids = buyBids,
    sellBids = sellBids,
    bookmarks = bookmarks
)
