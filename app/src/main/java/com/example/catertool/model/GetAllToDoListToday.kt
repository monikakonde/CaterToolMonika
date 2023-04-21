package com.example.catertool.model

data class GetAllToDoListToday(
    val `data`: List<Data>,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val assigned_to: AssignedTo,
            val brand_detail: BrandDetail,
            val createdAt: String,
            val deadlineDateTime: String,
            val notes: String,
            val status: String,
            val title: String,
            val updatedAt: String,
            var isSelected:Boolean
        ) {
            data class AssignedTo(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val allowChecks: Boolean,
                        val allowCompanyDetails: Boolean,
                        val allowHNSUnits: Boolean,
                        val allowSalesTracker: Boolean,
                        val allowTemperature: Boolean,
                        val allowToDo: Boolean,
                        val blocked: Boolean,
                        val confirmed: Boolean,
                        val createdAt: String,
                        val email: String,
                        val firstName: String,
                        val isAdmin: Boolean,
                        val lastName: String,
                        val mobile: String,
                        val otpConfirmed: Boolean,
                        val provider: String,
                        val telephone: Any,
                        val updatedAt: String,
                        val username: String
                    )
                }
            }

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