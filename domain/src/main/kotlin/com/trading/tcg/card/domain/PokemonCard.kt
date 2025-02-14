package com.trading.tcg.card.domain

import com.trading.tcg.product.domain.*
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "pokemon_card")
@DiscriminatorValue("POKEMON")
class PokemonCard(
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
        name = "pokemon_card_pack_catalog",
        joinColumns = [JoinColumn(name = "card_id")],
        inverseJoinColumns = [JoinColumn(name = "pack_id")]
    )
    val packs: List<PokemonCardPack>,

    @Column(name = "code", nullable = false)
    val code: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "image", nullable = false)
    val image: String,

    @Column(name = "categories", nullable = false)
    val categories: String,

    @Column(name = "type", nullable = false)
    val type: String?,

    @Column(name = "serial_code", nullable = false)
    val serialCode: String?,

    @Column(name = "sequence_number", nullable = false)
    val sequenceNumber: String?,

    @Column(name = "regulation_mark")
    val regulationMark: String?,

    @Column(name = "rank")
    val rank: String?,

    @Column(name = "level", nullable = false)
    val level: String?,

    @Column(name = "hp", nullable = false)
    val hp: String?,

    @Column(name = "weakness_type")
    val weaknessType: String?,

    @Column(name = "weakness_value")
    val weaknessValue: String?,

    @Column(name = "resistance_type")
    val resistanceType: String?,

    @Column(name = "resistance_value")
    val resistanceValue: String?,

    @Column(name = "retreat_value")
    val retreatValue: Int?,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val skills: List<PokemonCardSkill>
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
