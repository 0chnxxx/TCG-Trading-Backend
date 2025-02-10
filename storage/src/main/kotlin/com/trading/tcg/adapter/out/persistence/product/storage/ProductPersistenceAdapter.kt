package com.trading.tcg.adapter.out.persistence.product.storage

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.trading.tcg.adapter.out.persistence.digimon.entity.QDigimonCardEntity
import com.trading.tcg.adapter.out.persistence.pokemon.entity.QPokemonCardEntity
import com.trading.tcg.adapter.out.persistence.product.entity.*
import com.trading.tcg.adapter.out.persistence.user.entity.QUserProductBookmarkEntity
import com.trading.tcg.adapter.out.persistence.yugioh.entity.QYugiohCardEntity
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductCatalogDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.application.product.port.out.ProductPersistencePort
import com.trading.tcg.global.dto.Pageable
import org.springframework.stereotype.Repository

@Repository
class ProductPersistenceAdapter(
    val jpaQueryFactory: JPAQueryFactory
): ProductPersistencePort {
    override fun findProducts(query: FindProductsQuery): Pageable<List<ProductDto>> {
        val qProduct = QProductEntity.productEntity
        val qPokemonCard = QPokemonCardEntity.pokemonCardEntity
        val qYugiohCard = QYugiohCardEntity.yugiohCardEntity
        val qDigimonCard = QDigimonCardEntity.digimonCardEntity
        val qBuyBid = QProductBuyBidEntity.productBuyBidEntity
        val qSellBid = QProductSellBidEntity.productSellBidEntity
        val qBookmark = QUserProductBookmarkEntity.userProductBookmarkEntity

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        orderProducts(orderBuilder, query, qProduct, qBuyBid, qSellBid)
        filterProducts(whereBuilder, query, qProduct, qPokemonCard, qYugiohCard, qDigimonCard)

        val totalCount = countProducts(query)

        val products = jpaQueryFactory
            .select(
                Projections.constructor(
                    ProductDto::class.java,
                    qProduct.id,
                    Expressions.cases()
                        .`when`(qPokemonCard.name.isNotNull).then(qPokemonCard.name)
                        .`when`(qYugiohCard.name.isNotNull).then(qYugiohCard.name)
                        .`when`(qDigimonCard.name.isNotNull).then(qDigimonCard.name)
                        .otherwise(""),
                    Expressions.cases()
                        .`when`(qPokemonCard.name.isNotNull).then(qPokemonCard.image)
                        .`when`(qYugiohCard.name.isNotNull).then(qYugiohCard.image)
                        .`when`(qDigimonCard.name.isNotNull).then(qDigimonCard.image)
                        .otherwise(""),
                    qProduct.recentPrice,
                    qProduct.directBuyPrice,
                    qProduct.directSellPrice,
                    qProduct.dealCount,
                    qProduct.buyBidCount,
                    qProduct.sellBidCount,
                    qProduct.bookmarkCount,
                    qProduct.id.`in`(
                        ExpressionUtils.list(
                            Long::class.java,
                            JPAExpressions
                                .select(qBookmark.product.id)
                                .from(qBookmark)
                                .where(qBookmark.user.id.eq(query.userId))
                        )
                    ),
                    qProduct.createdTime,
                    qProduct.updatedTime
                )
            )
            .from(qProduct)
            .leftJoin(qPokemonCard).on(qPokemonCard.id.eq(qProduct.id))
            .leftJoin(qYugiohCard).on(qYugiohCard.id.eq(qProduct.id))
            .leftJoin(qDigimonCard).on(qDigimonCard.id.eq(qProduct.id))
            .leftJoin(qBuyBid).on(qBuyBid.product.id.eq(qProduct.id))
            .leftJoin(qSellBid).on(qSellBid.product.id.eq(qProduct.id))
            .leftJoin(qBookmark).on(qBookmark.product.id.eq(qProduct.id))
            .where(whereBuilder.value)
            .orderBy(*orderBuilder.toTypedArray())
            .offset(((query.page - 1) * query.size).toLong())
            .limit(query.size.toLong())
            .fetch()

        return Pageable(
            pageResult = Pageable.PageResult.of(
                totalCount = totalCount,
                page = query.page,
                size = query.size
            ),
            data = products
        )
    }

    override fun findProductCatalog(): ProductCatalogDto {
        val qProductCategory = QProductCategoryEntity.productCategoryEntity
        val qProductCategoryFilter = QProductCategoryFilterEntity.productCategoryFilterEntity

        return ProductCatalogDto(
            categories = jpaQueryFactory
                .from(qProductCategory)
                .leftJoin(qProductCategory.filters, qProductCategoryFilter)
                .groupBy(qProductCategory.id, qProductCategoryFilter.id)
                .orderBy(
                    qProductCategory.displayOrder.asc(),
                    qProductCategoryFilter.displayOrder.asc(),
                )
                .transform(
                    GroupBy.groupBy(qProductCategory.id)
                        .list(
                            Projections.constructor(
                                ProductCatalogDto.ProductCategory::class.java,
                                qProductCategory.queryName,
                                qProductCategory.displayName,
                                GroupBy.list(
                                    Projections.constructor(
                                        ProductCatalogDto.ProductFilter::class.java,
                                        qProductCategoryFilter.queryName,
                                        qProductCategoryFilter.displayName,
                                        qProductCategoryFilter.option
                                    )
                                )
                            )
                        )
                )
        )
    }

    override fun countProducts(query: FindProductsQuery): Long {
        val qProduct = QProductEntity.productEntity
        val qPokemonCard = QPokemonCardEntity.pokemonCardEntity
        val qYugiohCard = QYugiohCardEntity.yugiohCardEntity
        val qDigimonCard = QDigimonCardEntity.digimonCardEntity
        val qBuyBid = QProductBuyBidEntity.productBuyBidEntity
        val qSellBid = QProductSellBidEntity.productSellBidEntity
        val qBookmark = QUserProductBookmarkEntity.userProductBookmarkEntity

        val whereBuilder = BooleanBuilder()

        filterProducts(whereBuilder, query, qProduct, qPokemonCard, qYugiohCard, qDigimonCard)

        return jpaQueryFactory
            .select(qProduct.id.count())
            .from(qProduct)
            .leftJoin(qPokemonCard).on(qPokemonCard.id.eq(qProduct.id))
            .leftJoin(qYugiohCard).on(qYugiohCard.id.eq(qProduct.id))
            .leftJoin(qDigimonCard).on(qDigimonCard.id.eq(qProduct.id))
            .leftJoin(qBuyBid).on(qBuyBid.product.id.eq(qProduct.id))
            .leftJoin(qSellBid).on(qSellBid.product.id.eq(qProduct.id))
            .leftJoin(qBookmark).on(qBookmark.product.id.eq(qProduct.id))
            .where(whereBuilder.value)
            .fetchOne() ?: 0
    }

    private fun orderProducts(
        orderBuilder: MutableList<OrderSpecifier<*>>,
        query: FindProductsQuery,
        qProduct: QProductEntity,
        qBuyBid: QProductBuyBidEntity,
        qSellBid: QProductSellBidEntity
    ) {
        val sort = Order.valueOf(query.sort.uppercase())

        when (query.order) {
            "id" -> orderBuilder.add(OrderSpecifier(sort, qProduct.id))
            "bidPlacedTime" -> {
                orderBuilder.add(OrderSpecifier(sort, qBuyBid.createdTime))
                orderBuilder.add(OrderSpecifier(sort, qSellBid.createdTime))
            }

            "bidClosedTime" -> {
                orderBuilder.add(OrderSpecifier(sort, qBuyBid.closedTime))
                orderBuilder.add(OrderSpecifier(sort, qSellBid.closedTime))
            }

            "bidCount" -> orderBuilder.add(OrderSpecifier(sort, qProduct.buyBidCount.add(qProduct.sellBidCount)))
            "dealCount" -> orderBuilder.add(OrderSpecifier(sort, qProduct.dealCount))
            "price" -> orderBuilder.add(OrderSpecifier(sort, qProduct.recentPrice))
        }
    }

    private fun filterProducts(
        whereBuilder: BooleanBuilder,
        query: FindProductsQuery,
        qProduct: QProductEntity,
        qPokemonCard: QPokemonCardEntity,
        qYugiohCard: QYugiohCardEntity,
        qDigimonCard: QDigimonCardEntity
    ) {
        if (query.isExcludedNotBidProduct) {
            whereBuilder.and(qProduct.buyBids.isNotEmpty.or(qProduct.sellBids.isNotEmpty))
        }

        when (query.tab) {
            "pokemon" -> {
                whereBuilder.and(qPokemonCard.id.isNotNull)
                whereBuilder.and(qPokemonCard.name.contains(query.search))
                whereBuilder.and(combineExpressions(query.rank) { qPokemonCard.rank.eq(it) })
                whereBuilder.and(combineExpressions(query.category) { qPokemonCard.categories.any().category.name.eq(it) })
                whereBuilder.and(combineExpressions(query.type) { qPokemonCard.type.eq(it) })
                whereBuilder.and(combineExpressions(query.regulationMark) { qPokemonCard.regulationMark.eq(it) })
            }

            "yugioh" -> {
                whereBuilder.and(qYugiohCard.id.isNotNull)
                whereBuilder.and(qYugiohCard.name.contains(query.search))
                whereBuilder.and(combineExpressions(query.summonType) { qYugiohCard.summonType.eq(it) })
                whereBuilder.and(combineExpressions(query.species) { qYugiohCard.species.eq(it) })
                whereBuilder.and(combineExpressions(query.type) { qYugiohCard.type.eq(it) })
                whereBuilder.and(combineExpressions(query.effect) { qYugiohCard.effect.eq(it) })
                whereBuilder.and(combineExpressions(query.attribute) { qYugiohCard.attributes.any().attribute.name.eq(it) })
            }

            "digimon" -> {
                whereBuilder.and(qDigimonCard.id.isNotNull)
                whereBuilder.and(qDigimonCard.name.contains(query.search))
                whereBuilder.and(combineExpressions(query.rank) { qDigimonCard.rank.eq(it) })
                whereBuilder.and(combineExpressions(query.category) { qDigimonCard.category.eq(it) })
                whereBuilder.and(combineExpressions(query.type) { qDigimonCard.types.any().type.name.eq(it) })
                whereBuilder.and(combineExpressions(query.form) { qDigimonCard.form.eq(it) })
                whereBuilder.and(combineExpressions(query.species) { qDigimonCard.species.eq(it) })
            }
        }
    }

    private fun <T> combineExpressions(
        values: List<T>,
        predicate: (T) -> BooleanExpression
    ): BooleanExpression? {
        val combinedExpression = values
            .map(predicate)
            .reduceOrNull { acc, expression -> acc.or(expression) }

        return combinedExpression
    }
}
