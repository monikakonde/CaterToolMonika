package com.example.catertool.model

data class TodoDone(
    val `data`: Data,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val createdAt: String,
            val deadlineDateTime: String,
            val notes: String,
            val status: String,
            val title: String,
            val updatedAt: String
        )
    }

    class Meta
}