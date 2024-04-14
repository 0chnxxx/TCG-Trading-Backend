package com.trading.tcg.card.domain

data class Pack(
    val id: Long?,
    val name: String
) {
    companion object {
        @JvmStatic
        fun Pack(id: Long, name: String): Pack {
            return Pack(
                id,
                name
            )
        }
    }
}
