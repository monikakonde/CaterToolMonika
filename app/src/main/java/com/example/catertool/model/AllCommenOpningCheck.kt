package com.example.catertool.model

data class AllCommenOpningCheck(
    val `data`: List<Data>,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val brand_detail: BrandDetail,
            val checkName: String,
            val createdAt: String,
            val isClosingCheck: Boolean,
            val isOpeningCheck: Boolean,
            val updatedAt: String
        ) {
            data class BrandDetail(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val addressLine1: String,
                        val addressLine2: Any,
                        val businessEmail: String,
                        val companyName: String,
                        val county: Any,
                        val createdAt: String,
                        val postcode: String,
                        val town: Any,
                        val tradingName: String,
                        val updatedAt: String,
                        val vat_no: Any
                    )
                }
            }
        }
    }

    data class Meta(
        val pagination: Pagination
    ) {
        data class Pagination(
            val page: Int,
            val pageCount: Int,
            val pageSize: Int,
            val total: Int
        )
    }
}