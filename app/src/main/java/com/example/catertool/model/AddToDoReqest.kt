package com.example.catertool.model

data class AddToDoReqest(
    val `data`: Data
) {
    data class Data(
        val assigned_to: String,
        val brand_detail: String,
        val deadlineDateTime: String,
        val notes: String,
        val status: String,
        val title: String
    )
}