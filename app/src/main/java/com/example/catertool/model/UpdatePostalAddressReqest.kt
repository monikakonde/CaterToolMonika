package com.example.catertool.model

data class UpdatePostalAddressReqest(
    val `data`: Data
) {
    data class Data(
        val addressLine1: String,
        val addressLine2: String,
        val country: Int,
        val county: String,
        val postcode: String,
        val town: String
    )
}