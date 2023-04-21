package com.example.catertool.model

data class EventSelaseRecordList(
    val `data`: List<Data>,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val card: Double,
            val cash: Int,
            val createdAt: String,
            val eventDate: String,
            val eventName: String,
            val hns_unit: HnsUnit,
            val notes: String,
            val other: Int,
            val updatedAt: String
        ) {
            data class HnsUnit(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val createdAt: String,
                        val typeOfFood: String,
                        val unitDescription: String,
                        val unitName: String,
                        val updatedAt: String
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