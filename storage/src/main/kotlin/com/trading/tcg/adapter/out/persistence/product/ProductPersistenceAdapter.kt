package com.trading.tcg.adapter.out.persistence.product

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.trading.tcg.adapter.out.persistence.global.util.ExpressionUtil
import com.trading.tcg.application.product.dto.request.FindProductBidHistoryQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductDetailDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.application.product.port.out.ProductPersistencePort
import com.trading.tcg.card.domain.*
import com.trading.tcg.global.dto.Pageable
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.product.domain.*
import com.trading.tcg.product.exception.ProductErrorCode
import com.trading.tcg.user.domain.QUser
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ProductPersistenceAdapter(
    private val jpaQueryFactory: JPAQueryFactory,
    private val productCategoryJpaRepository: ProductCategoryJpaRepository,
    private val productDealJpaRepository: ProductDealJpaRepository
): ProductPersistencePort {
    override fun findProductDtos(query: FindProductsQuery): Pageable<List<ProductDto>> {
        val qUser = QUser.user
        val qProduct = QProduct.product
        val qPokemonCard = QPokemonCard.pokemonCard
        val qYugiohCard = QYugiohCard.yugiohCard
        val qDigimonCard = QDigimonCard.digimonCard
        val qProductBuyBid = QProductBuyBid.productBuyBid
        val qProductSellBid = QProductSellBid.productSellBid
        val qProductBookmark = QProductBookmark.productBookmark

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.name)

        when (query.order) {
            ProductOrderBy.BID_PLACED_TIME -> {
                orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.createdTime))
                orderBuilder.add(OrderSpecifier(sort, qProductSellBid.createdTime))
            }
            ProductOrderBy.BID_CLOSED_TIME -> {
                orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.closedTime))
                orderBuilder.add(OrderSpecifier(sort, qProductSellBid.closedTime))
            }
            ProductOrderBy.BID_COUNT -> orderBuilder.add(OrderSpecifier(sort, qProduct.buyBidCount.add(qProduct.sellBidCount)))
            ProductOrderBy.DEAL_COUNT -> orderBuilder.add(OrderSpecifier(sort, qProduct.dealCount))
            ProductOrderBy.PRICE -> orderBuilder.add(OrderSpecifier(sort, qProduct.recentDealPrice))
        }

        when (query.tab) {
            ProductTab.POKEMON -> {
                whereBuilder.and(qPokemonCard.id.isNotNull)
                if (query.search != null) whereBuilder.and(qPokemonCard.name.contains(query.search))
                whereBuilder.and(ExpressionUtil.orAll(query.rank) { qPokemonCard.rank.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.category) { qPokemonCard.categories.contains(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.type) { qPokemonCard.type.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.regulationMark) { qPokemonCard.regulationMark.eq(it) })
            }

            ProductTab.YUGIOH -> {
                whereBuilder.and(qYugiohCard.id.isNotNull)
                if (query.search != null) whereBuilder.and(qYugiohCard.name.contains(query.search))
                whereBuilder.and(ExpressionUtil.orAll(query.category) { qYugiohCard.categories.contains(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.type) { qYugiohCard.type.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.effect) { qYugiohCard.effect.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.species) { qYugiohCard.species.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.summonType) { qYugiohCard.summonType.eq(it) })
            }

            ProductTab.DIGIMON -> {
                whereBuilder.and(qDigimonCard.id.isNotNull)
                if (query.search != null) whereBuilder.and(qDigimonCard.name.contains(query.search))
                whereBuilder.and(ExpressionUtil.orAll(query.rank) { qDigimonCard.rank.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.category) { qDigimonCard.category.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.type) { qDigimonCard.types.contains(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.form) { qDigimonCard.form.eq(it) })
                whereBuilder.and(ExpressionUtil.orAll(query.species) { qDigimonCard.species.eq(it) })
            }

            else -> throw CustomException(ProductErrorCode.INVALID_PRODUCT_TAB)
        }

        if (query.isBookmarked != null) {
            whereBuilder.and(qProductBookmark.user.id.eq(query.userId))
        }

        if (query.isExcludedNotBidProduct != null && query.isExcludedNotBidProduct!!) {
            whereBuilder.and(qProduct.buyBids.isNotEmpty.or(qProduct.sellBids.isNotEmpty))
        }

        val totalCount = jpaQueryFactory
            .select(qProduct.id.countDistinct())
            .from(qProduct)
            .leftJoin(qPokemonCard).on(qPokemonCard.id.eq(qProduct.id))
            .leftJoin(qYugiohCard).on(qYugiohCard.id.eq(qProduct.id))
            .leftJoin(qDigimonCard).on(qDigimonCard.id.eq(qProduct.id))
            .leftJoin(qProductBuyBid).on(qProductBuyBid.product.id.eq(qProduct.id))
            .leftJoin(qProductSellBid).on(qProductSellBid.product.id.eq(qProduct.id))
            .leftJoin(qProductBookmark).on(qProductBookmark.product.id.eq(qProduct.id))
            .leftJoin(qUser).on(qProductBookmark.user.id.eq(qUser.id))
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
                                .select(qProductBookmark.product.id)
                                .from(qProductBookmark)
                                .where(qProductBookmark.user.id.eq(query.userId))
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
            .leftJoin(qProductBookmark).on(qProductBookmark.product.id.eq(qProduct.id))
            .leftJoin(qUser).on(qProductBookmark.user.id.eq(qUser.id))
            .where(whereBuilder.value)
            .orderBy(*orderBuilder.toTypedArray())
            .groupBy(qProduct.id)
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

    override fun findProductDto(query: FindProductQuery): ProductDetailDto? {
        val qProduct = QProduct.product
        val qPokemonCard = QPokemonCard.pokemonCard
        val qPokemonCardPack = QPokemonCardPack.pokemonCardPack
        val qPokemonCardPackCatalog = QPokemonCardPackCatalog.pokemonCardPackCatalog
        val qYugiohCard = QYugiohCard.yugiohCard
        val qYugiohCardPack = QYugiohCardPack.yugiohCardPack
        val qYugiohCardPackCatalog = QYugiohCardPackCatalog.yugiohCardPackCatalog
        val qDigimonCard = QDigimonCard.digimonCard
        val qDigimonCardPack = QDigimonCardPack.digimonCardPack
        val qProductBuyBid = QProductBuyBid.productBuyBid
        val qProductSellBid = QProductSellBid.productSellBid
        val qBookmark = QProductBookmark.productBookmark

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
                        qProductBuyBid.stock,
                        qProductSellBid.price,
                        qProductSellBid.stock,
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

    override fun findProductCategoriesWithFilters(): List<ProductCategory> {
        return productCategoryJpaRepository.findAllWithFilters()
    }

    override fun findProductBuyBids(query: FindProductBidHistoryQuery): Pageable<List<ProductBuyBid>> {
        val qUser = QUser.user
        val qProduct = QProduct.product
        val qProductBuyBid = QProductBuyBid.productBuyBid

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.name)

        when (query.order) {
            ProductBidOrderBy.CREATED_TIME -> orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.createdTime))
            ProductBidOrderBy.PRICE -> orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.price))
            ProductBidOrderBy.QUANTITY -> orderBuilder.add(OrderSpecifier(sort, qProductBuyBid.stock))
        }

        whereBuilder.and(qProductBuyBid.product.id.eq(query.productId))

        when (query.status) {
            ProductBidStatus.BIDDING -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.BIDDING))
            ProductBidStatus.DEALT -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.DEALT))
            ProductBidStatus.CANCELLED -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.CANCELLED))
            ProductBidStatus.CLOSED -> whereBuilder.and(qProductBuyBid.status.eq(ProductBidStatus.CLOSED))
            else -> throw CustomException(ProductErrorCode.INVALID_PRODUCT_BID_STATUS)
        }

        val totalCount = jpaQueryFactory
            .select(qProductBuyBid.id.countDistinct())
            .from(qProductBuyBid)
            .join(qProduct).on(qProductBuyBid.product.id.eq(qProduct.id))
            .join(qUser).on(qProductBuyBid.user.id.eq(qUser.id))
            .where(whereBuilder.value)
            .fetchOne() ?: 0

        val productBids = jpaQueryFactory
            .select(qProductBuyBid)
            .from(qProductBuyBid)
            .join(qProduct).on(qProductBuyBid.product.id.eq(qProduct.id)).fetchJoin()
            .join(qUser).on(qProductBuyBid.user.id.eq(qUser.id)).fetchJoin()
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

    override fun findProductSellBids(query: FindProductBidHistoryQuery): Pageable<List<ProductSellBid>> {
        val qUser = QUser.user
        val qProduct = QProduct.product
        val qProductSellBid = QProductSellBid.productSellBid

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.name)

        when (query.order) {
            ProductBidOrderBy.CREATED_TIME -> orderBuilder.add(OrderSpecifier(sort, qProductSellBid.createdTime))
            ProductBidOrderBy.PRICE -> orderBuilder.add(OrderSpecifier(sort, qProductSellBid.price))
            ProductBidOrderBy.QUANTITY -> orderBuilder.add(OrderSpecifier(sort, qProductSellBid.stock))
        }

        whereBuilder.and(qProductSellBid.product.id.eq(query.productId))

        when (query.status) {
            ProductBidStatus.BIDDING -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.BIDDING))
            ProductBidStatus.DEALT -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.DEALT))
            ProductBidStatus.CANCELLED -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.CANCELLED))
            ProductBidStatus.CLOSED -> whereBuilder.and(qProductSellBid.status.eq(ProductBidStatus.CLOSED))
            else -> throw CustomException(ProductErrorCode.INVALID_PRODUCT_BID_STATUS)
        }

        val totalCount = jpaQueryFactory
            .select(qProductSellBid.id.countDistinct())
            .from(qProductSellBid)
            .join(qProduct).on(qProductSellBid.product.id.eq(qProduct.id))
            .join(qUser).on(qProductSellBid.user.id.eq(qUser.id))
            .where(whereBuilder.value)
            .fetchOne() ?: 0

        val productBids = jpaQueryFactory
            .select(qProductSellBid)
            .from(qProductSellBid)
            .join(qProduct).on(qProductSellBid.product.id.eq(qProduct.id)).fetchJoin()
            .join(qUser).on(qProductSellBid.user.id.eq(qUser.id)).fetchJoin()
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

    override fun findProductDealBids(query: FindProductBidHistoryQuery): Pageable<List<ProductDealBid>> {
        val qProductBuyBid = QProductBuyBid.productBuyBid
        val qBuyer = QUser("qBuyer")
        val qProductSellBid = QProductSellBid.productSellBid
        val qSeller = QUser("qSeller")
        val qProductDealBid = QProductDealBid.productDealBid

        val whereBuilder = BooleanBuilder()
        val orderBuilder = mutableListOf<OrderSpecifier<*>>()

        val sort = Order.valueOf(query.sort.name)

        when (query.order) {
            ProductBidOrderBy.CREATED_TIME -> orderBuilder.add(OrderSpecifier(sort, qProductDealBid.createdTime))
            ProductBidOrderBy.PRICE -> orderBuilder.add(OrderSpecifier(sort, qProductDealBid.price))
            ProductBidOrderBy.QUANTITY -> orderBuilder.add(OrderSpecifier(sort, qProductDealBid.quantity))
        }

        whereBuilder.and(qProductDealBid.product.id.eq(query.productId))

        when (query.status) {
            ProductBidStatus.BIDDING -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.BIDDING)
                    .or(qProductSellBid.status.eq(ProductBidStatus.BIDDING))
            )

            ProductBidStatus.DEALT -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.DEALT).or(qProductSellBid.status.eq(ProductBidStatus.DEALT))
            )

            ProductBidStatus.CANCELLED -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.CANCELLED)
                    .or(qProductSellBid.status.eq(ProductBidStatus.CANCELLED))
            )

            ProductBidStatus.CLOSED -> whereBuilder.and(
                qProductBuyBid.status.eq(ProductBidStatus.CLOSED).or(qProductSellBid.status.eq(ProductBidStatus.CLOSED))
            )

            else -> throw CustomException(ProductErrorCode.INVALID_PRODUCT_BID_STATUS)
        }

        val totalCount = jpaQueryFactory
            .select(qProductDealBid.id.countDistinct())
            .from(qProductDealBid)
            .join(qProductDealBid.buyBid, qProductBuyBid)
            .join(qProductDealBid.buyBid.user, qBuyer)
            .join(qProductDealBid.sellBid, qProductSellBid)
            .join(qProductDealBid.sellBid.user, qSeller)
            .where(whereBuilder.value)
            .fetchOne() ?: 0

        val productBids = jpaQueryFactory
            .select(qProductDealBid)
            .from(qProductDealBid)
            .join(qProductDealBid.buyBid, qProductBuyBid).fetchJoin()
            .join(qProductDealBid.buyBid.user, qBuyer).fetchJoin()
            .join(qProductDealBid.sellBid, qProductSellBid).fetchJoin()
            .join(qProductDealBid.sellBid.user, qSeller).fetchJoin()
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

    override fun findProductDealsByProductIdAfterDateTime(
        productId: Long,
        dateTime: LocalDateTime
    ): List<ProductDealBid> {
        return productDealJpaRepository.findAllByProductIdAndCreatedTimeAfter(productId, dateTime)
    }
}
