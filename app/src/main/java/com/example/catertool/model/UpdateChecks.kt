package com.example.catertool.model

data class UpdateChecks(
    val `data`: Data,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val checkName: String,
            val createdAt: String,
            val isClosingCheck: Boolean,
            val isOpeningCheck: Boolean,
            val updatedAt: String
        )
    }

    class Meta
}