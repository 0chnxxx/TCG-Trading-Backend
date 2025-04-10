package com.trading.tcg.card.domain

import com.trading.tcg.product.domain.*
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "digimon_card")
@DiscriminatorValue("DIGIMON")
class DigimonCard(
    id: Long?,
    code: String,
    name: String,
    image: String,
    deals: List<ProductDealBid>,
    buyBids: List<ProductBuyBid>,
    sellBids: List<ProductSellBid>,
    bookmarks: List<ProductBookmark>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    val pack: DigimonCardPack,

    @Column(name = "rank", nullable = false)
    val rank: String,

    @Column(name = "category", nullable = false)
    val category: String,

    @Column(name = "types", nullable = false)
    val types: String,

    @Column(name = "form")
    val form: String?,

    @Column(name = "species")
    val species: String?,

    @Column(name = "level")
    val level: Int?,

    @Column(name = "dp")
    val dp: Int?,

    @Column(name = "appearance_cost")
    val appearanceCost: Int?,

    @Column(name = "evolution_cost_1")
    val evolutionCost1: String?,

    @Column(name = "evolution_cost_2")
    val evolutionCost2: String?,

    @Column(name = "top_description")
    val topDescription: String?,

    @Column(name = "bottom_description")
    val bottomDescription: String?
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
