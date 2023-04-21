package com.example.catertool.model

data class AddChecksReqest(
    val `data`: Data
) {
    data class Data(
        val brand_detail: Int,
        val checkName: String,
        val isClosingCheck: Int,
        val isOpeningCheck: Int
    )
}