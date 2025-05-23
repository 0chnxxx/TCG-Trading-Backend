package com.trading.tcg.product.exception

import com.trading.tcg.global.exception.ErrorCode

enum class ProductErrorCode(
    override val statusCode: Int,
    override val errorCode: String,
    override val errorMessage: String
) : ErrorCode {
    NOT_FOUND_PRODUCT(404, "NOT_FOUND_PRODUCT", "상품이 존재하지 않습니다."),
    INVALID_PRODUCT_ORDER(400, "INVALID_PRODUCT_ORDER", "유효한 상품 정렬 대상이 아닙니다."),
    INVALID_PRODUCT_TAB(400, "INVALID_PRODUCT_TAB", "유효한 상품 탭이 아닙니다."),
    INVALID_PRODUCT_BID_ORDER(400, "INVALID_PRODUCT_BID_ORDER", "유효한 상품 입찰 정렬 방식이 아닙니다."),
    INVALID_PRODUCT_BID_TYPE(400, "INVALID_PRODUCT_BID_TYPE", "유효한 상품 입찰 타입이 아닙니다."),
    INVALID_PRODUCT_BID_STATUS(400, "INVALID_PRODUCT_BID_STATUS", "유효한 상품 입찰 상태가 아닙니다.");
}
