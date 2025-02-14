package com.trading.tcg.application.product.dto.response

import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.util.BigDecimalUtil.toDisplayString
import com.trading.tcg.product.domain.ProductBuyBid
import com.trading.tcg.product.domain.ProductDealBid
import com.trading.tcg.product.domain.ProductSellBid
import com.trading.tcg.user.domain.User
import java.time.LocalDateTime

data class ProductBidHistoryDto(
    val price: String,
    val quantity: Int,
    val date: LocalDateTime,
    val isMine: Boolean
) {
    companion object {
        fun ofBuy(provider: Provider, productBuyBid: ProductBuyBid): ProductBidHistoryDto {
            return ProductBidHistoryDto(
                price = productBuyBid.price.toDisplayString(),
                quantity = productBuyBid.stock,
                date = productBuyBid.createdTime,
                isMine = productBuyBid.user.id == provider.getUser()?.id
            )
        }

        fun ofSell(provider: Provider, productSellBid: ProductSellBid): ProductBidHistoryDto {
            return ProductBidHistoryDto(
                price = productSellBid.price.toDisplayString(),
                quantity = productSellBid.stock,
                date = productSellBid.createdTime,
                isMine = productSellBid.user.id == provider.getUser()?.id
            )
        }

        fun ofDeal(provider: Provider, productDealBid: ProductDealBid): ProductBidHistoryDto {
            return ProductBidHistoryDto(
                price = productDealBid.price.toDisplayString(),
                quantity = productDealBid.quantity,
                date = productDealBid.createdTime,
                isMine = productDealBid.buyBid.user.id == provider.getUser()?.id
                        || productDealBid.sellBid.user.id == provider.getUser()?.id
            )
        }
    }
}
