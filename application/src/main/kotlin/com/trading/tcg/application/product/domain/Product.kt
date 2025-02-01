package com.trading.tcg.application.product.domain

import java.time.LocalDateTime

class Product(
    val id: Long?,
    //최근거래가
    //즉시구매가
    //즉시판매가
    //
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
)
