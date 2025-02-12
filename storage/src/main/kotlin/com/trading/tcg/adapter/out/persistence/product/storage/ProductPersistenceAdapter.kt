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
import com.trading.tcg.adapter.out.persistence.card.entity.*
import com.trading.tcg.adapter.out.persistence.product.entity.*
import com.trading.tcg.adapter.out.persistence.user.entity.QUserEntity
import com.trading.tcg.adapter.out.persistence.user.entity.QUserProductBookmarkEntity
import com.trading.tcg.application.product.domain.ProductBidStatus
import com.trading.tcg.application.product.dto.request.FindProductBidsQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductBidDto
import com.trading.tcg.application.product.dto.response.ProductCatalogDto
import com.trading.tcg.application.product.dto.response.ProductDetailDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.application.product.port.out.ProductPersistencePort
import com.trading.tcg.global.dto.Pageable
import org.springframework.stereotype.Repository

@Repository
class ProductPersistenceAdapter(
    val jpaQueryFactory: JPAQueryFactory
): ProductPersistencePort {
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

    override fun findProducts(query: FindProductsQuery): Pageable<List<ProductDto>> {
        val qProduct = QProductEntity.productEntity
        val qPokemonCard = QPokemonCardEntity.pokemonCardEntity
        val qYugiohCard = QYugiohCardEntity.yugiohCardEntity
        val qDigimonCard = QDigimonCardEntity.digimonCardEntity
        val qProductBuyBid = QProductBuyBidEntity.productBuyBidEntity
        val qProductSellBid = QProductSellBidEntity.productSellBidEntity
        val qBookmark = QUserProductBookmarkEntity.userProductBookmarkEntity

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.uppercase())

        when (query.order) {
            "id" -> orderBuilder.add(OrderSpecifier(sort, qProduct.id))
            "bidPlacedTime" -> {
                orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.createdTime))
                orderBuilder.add(OrderSpecifier(sort, qProductSellBid.createdTime))
            }

            "bidClosedTime" -> {
                orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.closedTime))
                orderBuilder.add(OrderSpecifier(sort, qProductSellBid.closedTime))
            }

            "bidCount" -> orderBuilder.add(OrderSpecifier(sort, qProduct.buyBidCount.add(qProduct.sellBidCount)))
            "dealCount" -> orderBuilder.add(OrderSpecifier(sort, qProduct.dealCount))
            "price" -> orderBuilder.add(OrderSpecifier(sort, qProduct.recentDealPrice))
        }

        if (query.isExcludedNotBidProduct) {
            whereBuilder.and(qProduct.buyBids.isNotEmpty.or(qProduct.sellBids.isNotEmpty))
        }

        when (query.tab) {
            "pokemon" -> {
                whereBuilder.and(qPokemonCard.id.isNotNull)
                whereBuilder.and(qPokemonCard.name.contains(query.search))
                whereBuilder.and(combineExpressions(query.rank) { qPokemonCard.rank.eq(it) })
                whereBuilder.and(combineExpressions(query.category) { qPokemonCard.categories.contains(it) })
                whereBuilder.and(combineExpressions(query.type) { qPokemonCard.type.eq(it) })
                whereBuilder.and(combineExpressions(query.regulationMark) { qPokemonCard.regulationMark.eq(it) })
            }

            "yugioh" -> {
                whereBuilder.and(qYugiohCard.id.isNotNull)
                whereBuilder.and(qYugiohCard.name.contains(query.search))
                whereBuilder.and(combineExpressions(query.category) { qYugiohCard.categories.contains(it) })
                whereBuilder.and(combineExpressions(query.type) { qYugiohCard.type.eq(it) })
                whereBuilder.and(combineExpressions(query.effect) { qYugiohCard.effect.eq(it) })
                whereBuilder.and(combineExpressions(query.species) { qYugiohCard.species.eq(it) })
                whereBuilder.and(combineExpressions(query.summonType) { qYugiohCard.summonType.eq(it) })
            }

            "digimon" -> {
                whereBuilder.and(qDigimonCard.id.isNotNull)
                whereBuilder.and(qDigimonCard.name.contains(query.search))
                whereBuilder.and(combineExpressions(query.rank) { qDigimonCard.rank.eq(it) })
                whereBuilder.and(combineExpressions(query.category) { qDigimonCard.category.eq(it) })
                whereBuilder.and(combineExpressions(query.type) { qDigimonCard.types.contains(it) })
                whereBuilder.and(combineExpressions(query.form) { qDigimonCard.form.eq(it) })
                whereBuilder.and(combineExpressions(query.species) { qDigimonCard.species.eq(it) })
            }
        }

        val totalCount = jpaQueryFactory
            .select(qProduct.id.countDistinct())
            .from(qProduct)
            .leftJoin(qPokemonCard).on(qPokemonCard.id.eq(qProduct.id))
            .leftJoin(qYugiohCard).on(qYugiohCard.id.eq(qProduct.id))
            .leftJoin(qDigimonCard).on(qDigimonCard.id.eq(qProduct.id))
            .leftJoin(qProductBuyBid).on(qProductBuyBid.product.id.eq(qProduct.id))
            .leftJoin(qProductSellBid).on(qProductSellBid.product.id.eq(qProduct.id))
            .where(whereBuilder.value)
            .fetchOne() ?: 0

        val products = jpaQueryFactory
            .select(
                Projections.constructor(
                    ProductDto::class.java,
                    qProduct.id,
                    Expressions.cases()
                        .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.name)
                        .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.name)
                        .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.name)
                        .otherwise(Expressions.nullExpression()),
                    Expressions.cases()
                        .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.image)
                        .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.image)
                        .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.image)
                        .otherwise(Expressions.nullExpression()),
                    qProduct.recentDealPrice,
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
            .leftJoin(qProductBuyBid).on(qProductBuyBid.product.id.eq(qProduct.id))
            .leftJoin(qProductSellBid).on(qProductSellBid.product.id.eq(qProduct.id))
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

    private fun orderProducts(
        orderBuilder: MutableList<OrderSpecifier<*>>,
        query: FindProductsQuery,
        qProduct: QProductEntity,
        qProductBuyBid: QProductBuyBidEntity,
        qProductSellBid: QProductSellBidEntity
    ) {
        val sort = Order.valueOf(query.sort.uppercase())

        when (query.order) {
            "id" -> orderBuilder.add(OrderSpecifier(sort, qProduct.id))
            "bidPlacedTime" -> {
                orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.createdTime))
                orderBuilder.add(OrderSpecifier(sort, qProductSellBid.createdTime))
            }

            "bidClosedTime" -> {
                orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.closedTime))
                orderBuilder.add(OrderSpecifier(sort, qProductSellBid.closedTime))
            }

            "bidCount" -> orderBuilder.add(OrderSpecifier(sort, qProduct.buyBidCount.add(qProduct.sellBidCount)))
            "dealCount" -> orderBuilder.add(OrderSpecifier(sort, qProduct.dealCount))
            "price" -> orderBuilder.add(OrderSpecifier(sort, qProduct.recentDealPrice))
        }
    }

    override fun findProduct(query: FindProductQuery): ProductDetailDto? {
        val qProduct = QProductEntity.productEntity
        val qPokemonCard = QPokemonCardEntity.pokemonCardEntity
        val qPokemonCardPack = QPokemonCardPackEntity.pokemonCardPackEntity
        val qPokemonCardPackCatalog = QPokemonCardPackCatalogEntity.pokemonCardPackCatalogEntity
        val qYugiohCard = QYugiohCardEntity.yugiohCardEntity
        val qYugiohCardPack = QYugiohCardPackEntity.yugiohCardPackEntity
        val qYugiohCardPackCatalog = QYugiohCardPackCatalogEntity.yugiohCardPackCatalogEntity
        val qDigimonCard = QDigimonCardEntity.digimonCardEntity
        val qDigimonCardPack = QDigimonCardPackEntity.digimonCardPackEntity
        val qProductBuyBid = QProductBuyBidEntity.productBuyBidEntity
        val qProductSellBid = QProductSellBidEntity.productSellBidEntity
        val qBookmark = QUserProductBookmarkEntity.userProductBookmarkEntity

        val maxBuySubQuery = JPAExpressions
            .select(qProductBuyBid.id)
            .from(qProductBuyBid)
            .where(
                qProductBuyBid.product.id.eq(qProduct.id)
                    .and(qProductBuyBid.status.eq(ProductBidStatus.BIDDING))
            )
            .orderBy(qProductBuyBid.price.desc())
            .limit(1)

        val minSellSubQuery = JPAExpressions
            .select(qProductSellBid.id)
            .from(qProductSellBid)
            .where(
                qProductSellBid.product.id.eq(qProduct.id)
                    .and(qProductSellBid.status.eq(ProductBidStatus.BIDDING))
            )
            .orderBy(qProductSellBid.price.asc())
            .limit(1)

        return jpaQueryFactory
            .from(qProduct)
            .leftJoin(qPokemonCard).on(qPokemonCard.id.eq(qProduct.id))
            .leftJoin(qPokemonCardPackCatalog).on(qPokemonCardPackCatalog.card.id.eq(qProduct.id))
            .leftJoin(qPokemonCardPack).on(qPokemonCardPack.id.eq(qPokemonCardPackCatalog.pack.id))
            .leftJoin(qYugiohCard).on(qYugiohCard.id.eq(qProduct.id))
            .leftJoin(qYugiohCardPackCatalog).on(qYugiohCardPackCatalog.card.id.eq(qProduct.id))
            .leftJoin(qYugiohCardPack).on(qYugiohCardPack.id.eq(qYugiohCardPackCatalog.pack.id))
            .leftJoin(qDigimonCard).on(qDigimonCard.id.eq(qProduct.id))
            .leftJoin(qDigimonCardPack).on(qDigimonCardPack.id.eq(qDigimonCard.pack.id))
            .leftJoin(qProductBuyBid).on(qProductBuyBid.id.eq(maxBuySubQuery))
            .leftJoin(qProductSellBid).on(qProductSellBid.id.eq(minSellSubQuery))
            .where(qProduct.id.eq(query.productId))
            .transform(
                GroupBy.groupBy(qProduct.id).list(
                    Projections.constructor(
                        ProductDetailDto::class.java,
                        qProduct.id,
                        GroupBy.list(
                            Expressions.cases()
                                .`when`(qPokemonCard.id.isNotNull).then(qPokemonCardPack.name)
                                .`when`(qYugiohCard.id.isNotNull).then(qYugiohCardPack.name)
                                .`when`(qDigimonCard.id.isNotNull).then(qDigimonCardPack.name)
                                .otherwise(Expressions.nullExpression())
                        ),
                        Expressions.cases()
                            .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.name)
                            .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.name)
                            .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.name)
                            .otherwise(Expressions.nullExpression()),
                        Expressions.cases()
                            .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.image)
                            .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.image)
                            .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.image)
                            .otherwise(Expressions.nullExpression()),
                        Expressions.cases()
                            .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.code)
                            .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.code)
                            .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.code)
                            .otherwise(Expressions.nullExpression()),
                        Expressions.cases()
                            .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.rank)
                            .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.rank)
                            .`when`(qYugiohCard.id.isNotNull).then(Expressions.nullExpression())
                            .otherwise(Expressions.nullExpression()),
                        Expressions.cases()
                            .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.categories)
                            .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.categories)
                            .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.category)
                            .otherwise(Expressions.nullExpression()),
                        Expressions.cases()
                            .`when`(qPokemonCard.id.isNotNull).then(qPokemonCard.type)
                            .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.type)
                            .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.types)
                            .otherwise(Expressions.nullExpression()),
                        Expressions.cases()
                            .`when`(qYugiohCard.id.isNotNull).then(qYugiohCard.species)
                            .`when`(qDigimonCard.id.isNotNull).then(qDigimonCard.form)
                            .`when`(qPokemonCard.id.isNotNull).then(Expressions.nullExpression())
                            .otherwise(Expressions.nullExpression()),
                        qProductBuyBid.price,
                        qProductBuyBid.remainingQuantity,
                        qProductSellBid.price,
                        qProductSellBid.remainingQuantity,
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
            ).firstOrNull()
    }

    override fun findProductBuyBids(query: FindProductBidsQuery): Pageable<List<ProductBidDto>> {
        val qUser = QUserEntity.userEntity
        val qProductBuyBid = QProductBuyBidEntity.productBuyBidEntity

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.uppercase())

        when (query.order) {
            "createdTime" -> orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.createdTime))
            "price" -> orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.price))
            "quantity" -> orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.remainingQuantity))
        }

        whereBuilder.and(qProductBuyBid.product.id.eq(query.productId))

        when (query.status) {
            "bidding" -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.BIDDING))
            "dealt" -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.DEALT))
            "cancelled" -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.CANCELLED))
            "closed" -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.CLOSED))
        }

        val totalCount = jpaQueryFactory
            .select(qProductBuyBid.id.countDistinct())
            .from(qProductBuyBid)
            .join(qUser).on(qProductBuyBid.user.id.eq(qUser.id))
            .where(whereBuilder.value)
            .fetchOne() ?: 0

        val productBids = jpaQueryFactory
            .select(
                Projections.constructor(
                    ProductBidDto::class.java,
                    qProductBuyBid.price,
                    qProductBuyBid.remainingQuantity,
                    qProductBuyBid.createdTime,
                    qProductBuyBid.user.id.eq(query.userId)
                )
            )
            .from(qProductBuyBid)
            .join(qUser).on(qProductBuyBid.user.id.eq(qUser.id))
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
            data = productBids
        )
    }

    override fun findProductSellBids(query: FindProductBidsQuery): Pageable<List<ProductBidDto>> {
        val qUser = QUserEntity.userEntity
        val qProductSellBid = QProductSellBidEntity.productSellBidEntity

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.uppercase())

        when (query.order) {
            "createdTime" -> orderBuilder.add(OrderSpecifier(sort, qProductSellBid.createdTime))
            "price" -> orderBuilder.add(OrderSpecifier(sort, qProductSellBid.price))
            "quantity" -> orderBuilder.add(OrderSpecifier(sort, qProductSellBid.remainingQuantity))
        }

        whereBuilder.and(qProductSellBid.product.id.eq(query.productId))

        when (query.status) {
            "bidding" -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.BIDDING))
            "dealt" -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.DEALT))
            "cancelled" -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.CANCELLED))
            "closed" -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.CLOSED))
        }

        val totalCount = jpaQueryFactory
            .select(qProductSellBid.id.countDistinct())
            .from(qProductSellBid)
            .join(qUser).on(qProductSellBid.user.id.eq(qUser.id))
            .where(whereBuilder.value)
            .fetchOne() ?: 0

        val productBids = jpaQueryFactory
            .select(
                Projections.constructor(
                    ProductBidDto::class.java,
                    qProductSellBid.price,
                    qProductSellBid.remainingQuantity,
                    qProductSellBid.createdTime,
                    qProductSellBid.user.id.eq(query.userId)
                )
            )
            .from(qProductSellBid)
            .join(qUser).on(qProductSellBid.user.id.eq(qUser.id))
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
            data = productBids
        )
    }

    override fun findProductDealBids(query: FindProductBidsQuery): Pageable<List<ProductBidDto>> {
        val qProductBuyBid = QProductBuyBidEntity.productBuyBidEntity
        val qBuyer = QUserEntity("qBuyer")
        val qProductSellBid = QProductSellBidEntity.productSellBidEntity
        val qSeller = QUserEntity("qSeller")
        val qProductDeal = QProductDealEntity.productDealEntity

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.uppercase())

        when (query.order) {
            "createdTime" -> orderBuilder.add(OrderSpecifier(sort, qProductDeal.createdTime))
            "price" -> orderBuilder.add(OrderSpecifier(sort, qProductDeal.price))
            "quantity" -> orderBuilder.add(OrderSpecifier(sort, qProductDeal.quantity))
        }

        whereBuilder.and(qProductDeal.product.id.eq(query.productId))

        when (query.status) {
            "bidding" -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.BIDDING)
                    .or(qProductSellBid.status.eq(ProductBidStatus.BIDDING))
            )

            "dealt" -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.DEALT).or(qProductSellBid.status.eq(ProductBidStatus.DEALT))
            )

            "cancelled" -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.CANCELLED)
                    .or(qProductSellBid.status.eq(ProductBidStatus.CANCELLED))
            )

            "closed" -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.CLOSED).or(qProductSellBid.status.eq(ProductBidStatus.CLOSED))
            )
        }

        val totalCount = jpaQueryFactory
            .select(qProductDeal.id.countDistinct())
            .from(qProductDeal)
            .join(qProductBuyBid).on(qProductDeal.buyBid.id.eq(qProductBuyBid.id))
            .join(qBuyer).on(qProductBuyBid.user.id.eq(qBuyer.id))
            .join(qProductSellBid).on(qProductDeal.sellBid.id.eq(qProductSellBid.id))
            .join(qSeller).on(qProductSellBid.user.id.eq(qSeller.id))
            .where(whereBuilder.value)
            .fetchOne() ?: 0

        val productBids = jpaQueryFactory
            .select(
                Projections.constructor(
                    ProductBidDto::class.java,
                    qProductDeal.price,
                    qProductDeal.quantity,
                    qProductDeal.createdTime,
                    qProductDeal.buyBid.user.id.eq(query.userId).or(qProductDeal.sellBid.user.id.eq(query.userId))
                )
            )
            .from(qProductDeal)
            .join(qProductBuyBid).on(qProductDeal.buyBid.id.eq(qProductBuyBid.id))
            .join(qBuyer).on(qProductBuyBid.user.id.eq(qBuyer.id))
            .join(qProductSellBid).on(qProductDeal.sellBid.id.eq(qProductSellBid.id))
            .join(qSeller).on(qProductSellBid.user.id.eq(qSeller.id))
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
            data = productBids
        )
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
