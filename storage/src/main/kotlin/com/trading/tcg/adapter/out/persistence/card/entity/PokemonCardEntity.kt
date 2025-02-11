package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.product.entity.ProductBidEntity
import com.trading.tcg.adapter.out.persistence.product.entity.ProductDealEntity
import com.trading.tcg.adapter.out.persistence.product.entity.ProductEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserProductBookmarkEntity
import com.trading.tcg.application.card.domain.PokemonCard
import com.trading.tcg.application.product.domain.Product
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "pokemon_card")
@DiscriminatorValue("POKEMON")
class PokemonCardEntity(
    id: Long?,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val packs: List<PokemonCardPackCatalogEntity>,

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
    val skills: List<PokemonCardSkillEntity>,

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
            card = PokemonCard(
                packs = packs.map { it.pack.toDomain() },
                code = code,
                name = name,
                image = image,
                categories = emptyList(),
                type = type,
                serialCode = serialCode,
                sequenceNumber = sequenceNumber,
                regulationMark = regulationMark,
                rank = rank,
                level = level,
                hp = hp,
                weaknessType = weaknessType,
                weaknessValue = weaknessValue,
                resistanceType = resistanceType,
                resistanceValue = resistanceValue,
                retreatValue = retreatValue,
                skills = emptyList()
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
