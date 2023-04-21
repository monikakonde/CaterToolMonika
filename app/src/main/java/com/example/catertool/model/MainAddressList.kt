package com.example.catertool.model

data class MainAddressList(
    val Items: List<Item>
) {
    data class Item(
        val Description: String,
        val Highlight: String,
        val Id: String,
        val Text: String,
        val Type: String
    )
}