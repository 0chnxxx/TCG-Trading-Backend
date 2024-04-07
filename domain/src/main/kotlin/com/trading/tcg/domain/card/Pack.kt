package com.trading.tcg.domain.card

data class Pack(
    val id: Long?,
    val name: String
) {
    companion object {
        fun Pack(id: Long, name: String): Pack {
            return Pack(
                id,
                name
            )
        }
    }
}
