package com.trading.tcg.adapter.`in`.api.banner.dto

import com.trading.tcg.application.banner.dto.request.FindBannersQuery
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.RequestErrorCode

data class FindBannersRequest(
    val page: Int?,
    val size: Int?
) {
    fun toQuery(): FindBannersQuery {
        return FindBannersQuery(
            page = page?.also { if (it < 1) throw CustomException(RequestErrorCode.OUT_OF_PAGE) } ?: 1,
            size = size?.also { if (it < 1) throw CustomException(RequestErrorCode.OUT_OF_SIZE) } ?: 10
        )
    }
}
