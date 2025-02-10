package com.trading.tcg.application.product.dto.response

data class ProductCatalogDto(
    val categories: List<ProductCategory>
) {
    data class ProductCategory(
        val queryName: String,
        val displayName: String,
        val filters: List<ProductFilter>,
    )

    data class ProductFilter(
        val queryName: String,
        val displayName: String,
        val options: List<String>
    ) {
        constructor(
            queryName: String,
            displayName: String,
            options: String
        ): this(
            queryName = queryName,
            displayName = displayName,
            options = options.split("\n")
        )
    }
}
