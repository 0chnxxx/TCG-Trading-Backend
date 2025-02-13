package com.trading.tcg.product.exception

import com.trading.tcg.global.exception.ErrorCode

enum class ProductErrorCode(
    private val statusCode: Int,
    private val errorMessage: String
) : ErrorCode {
    NOT_FOUND_PRODUCT(404, "상품이 존재하지 않습니다."),
    INVALID_PRODUCT_BID_TYPE(400, "유효한 상품 입찰 타입이 아닙니다."),
    INVALID_PRODUCT_BID_STATUS(400, "유효한 상품 입찰 상태가 아닙니다.");

    override fun getStatusCode() = statusCode
    override fun getErrorCode() = this.name
    override fun getErrorMessage() = errorMessage
}
