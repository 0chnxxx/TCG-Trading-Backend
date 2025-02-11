package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.product.entity.ProductBuyBidEntity
import com.trading.tcg.adapter.out.persistence.product.entity.ProductDealEntity
import com.trading.tcg.adapter.out.persistence.product.entity.ProductEntity
import com.trading.tcg.adapter.out.persistence.product.entity.ProductSellBidEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserProductBookmarkEntity
import com.trading.tcg.application.card.domain.YugiohCard
import com.trading.tcg.application.product.domain.Product
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "yugioh_card")
@DiscriminatorValue("YUGIOH")
class YugiohCardEntity(
    id: Long?,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val packs: List<YugiohCardPackCatalogEntity>,

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
    val description: String?,

    recentDealPrice: BigDecimal?,

    dealCount: Int,

    deals: List<ProductDealEntity>,

    directBuyPrice: BigDecimal?,

    buyBidCount: Int,

    buyBids: List<ProductBuyBidEntity>,

    directSellPrice: BigDecimal?,

    sellBidCount: Int,

    sellBids: List<ProductSellBidEntity>,

    bookmarkCount: Int,

    bookmarks: List<UserProductBookmarkEntity>
): ProductEntity(
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
) {
    override fun toDomain(): Product {
        return Product(
            id = id,
            card = YugiohCard(
                packs = packs.map { it.pack.toDomain() },
                code = code,
                name = name,
                image = image,
                categories = categories.split("\n"),
                type = type,
                effect = effect,
                species = species,
                summonType = summonType,
                summonValue = summonValue,
                marker = marker,
                attack = attack,
                defence = defence,
                pendulumScale = pendulumScale,
                pendulumDescription = pendulumDescription,
                description = description
            ),
            recentDealPrice = recentDealPrice,
            directBuyPrice = directBuyPrice,
            directSellPrice = directSellPrice,
            deals = emptyList(),
            buyBids = emptyList(),
            sellBids = emptyList(),
            bookmarks = emptyList(),
            createdTime = createdTime,
            updatedTime = updatedTime
        )
    }
}
