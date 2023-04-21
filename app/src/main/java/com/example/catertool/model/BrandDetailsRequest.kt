package com.example.catertool.model

data class BrandDetailsRequest(
    val `data`: Data
) {
    data class Data(
        val businessType: Int,
        val companyName: String,
        val tradingName: String,
        val typeOfBusiness: Int
    )

}