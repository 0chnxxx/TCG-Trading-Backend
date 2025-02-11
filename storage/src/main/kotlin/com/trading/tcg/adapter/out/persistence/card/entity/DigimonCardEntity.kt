package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.product.entity.ProductBidEntity
import com.trading.tcg.adapter.out.persistence.product.entity.ProductDealEntity
import com.trading.tcg.adapter.out.persistence.product.entity.ProductEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserProductBookmarkEntity
import com.trading.tcg.application.card.domain.DigimonCard
import com.trading.tcg.application.product.domain.Product
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "digimon_card")
@DiscriminatorValue("DIGIMON")
class DigimonCardEntity(
    id: Long?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    val pack: DigimonCardPackEntity,

    @Column(name = "code", nullable = false)
    val code: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "image", nullable = false)
    val image: String,

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
    val bottomDescription: String?,

    recentDealPrice: BigDecimal?,

    dealCount: Int,

    deals: List<ProductDealEntity>,

    directBuyPrice: BigDecimal?,

    buyBidCount: Int,

    directSellPrice: BigDecimal?,

    sellBidCount: Int,

    bids: List<ProductBidEntity>,

    bookmarkCount: Int,

    bookmarks: List<UserProductBookmarkEntity>
): ProductEntity(
    id = id,
    recentDealPrice = recentDealPrice,
    dealCount = dealCount,
    deals = deals,
    directBuyPrice = directBuyPrice,
    buyBidCount = buyBidCount,
    directSellPrice = directSellPrice,
    sellBidCount = sellBidCount,
    bids = bids,
    bookmarkCount = bookmarkCount,
    bookmarks = bookmarks
) {
    override fun toDomain(): Product {
        return Product(
            id = id,
            card = DigimonCard(
                pack = pack.toDomain(),
                code = code,
                name = name,
                image = image,
                rank = rank,
                category = category,
                types = types.split("\n"),
                form = form,
                species = species,
                level = level,
                dp = dp,
                appearanceCost = appearanceCost,
                evolutionCost1 = evolutionCost1,
                evolutionCost2 = evolutionCost2,
                topDescription = topDescription,
                bottomDescription = bottomDescription
            ),
            recentDealPrice = recentDealPrice,
            directBuyPrice = directBuyPrice,
            directSellPrice = directSellPrice,
            dealCount = dealCount,
            buyBidCount = buyBidCount,
            sellBidCount = sellBidCount,
            bookmarkCount = bookmarkCount,
            createdTime = createdTime,
            updatedTime = updatedTime
        )
    }
}
