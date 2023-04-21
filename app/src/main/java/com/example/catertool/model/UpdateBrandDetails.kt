package com.example.catertool.model

data class UpdateBrandDetails(
    val `data`: Data,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int) {
        data class Attributes(val addressLine1: String,
            val addressLine2: String,
            val businessEmail: String,
            val companyName: String,
            val county: String,
            val createdAt: String,
            val postcode: String,
            val town: String,
            val tradingName: String,
            val updatedAt: String,
            val vat_no: Any
        )
    }

    class Meta
}