package com.example.catertool.model

data class GetBrandDetails(
    val `data`: List<Data>,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val addressLine1: String,
            val addressLine2: String,
            val businessEmail: String,
            val businessType: BusinessType,
            val common_checks: CommonChecks,
            val companyLogo: CompanyLogo,
            val companyName: String,
            val country: Country,
            val county: String,
            val createdAt: String,
            val event_todo_items: EventTodoItems,
            val hns_units: HnsUnits,
            val organisation_detail: OrganisationDetail,
            val postcode: String,
            val temperature_sheet_templates: TemperatureSheetTemplates,
            val town: String,
            val tradingName: String,
            val typeOfBusiness: TypeOfBusiness,
            val updatedAt: String,
            val users: Users,
            val vat_no: Any
        ) {
            data class BusinessType(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val Name: String,
                        val createdAt: String,
                        val updatedAt: String
                    )
                }
            }

            data class CommonChecks(
                val `data`: List<Data>
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
            }

            data class CompanyLogo(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val alternativeText: String,
                        val caption: String,
                        val createdAt: String,
                        val ext: String,
                        val formats: Formats,
                        val hash: String,
                        val height: Int,
                        val mime: String,
                        val name: String,
                        val previewUrl: Any,
                        val provider: String,
                        val provider_metadata: Any,
                        val size: Double,
                        val updatedAt: String,
                        val url: String,
                        val width: Int
                    ) {
                        data class Formats(
                            val thumbnail: Thumbnail
                        ) {
                            data class Thumbnail(
                                val ext: String,
                                val hash: String,
                                val height: Int,
                                val mime: String,
                                val name: String,
                                val path: Any,
                                val size: Double,
                                val url: String,
                                val width: Int
                            )
                        }
                    }
                }
            }

            data class Country(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val Name: String,
                        val createdAt: String,
                        val updatedAt: String
                    )
                }
            }

            data class EventTodoItems(
                val `data`: List<Any>
            )

            data class HnsUnits(
                val `data`: List<Data>
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val createdAt: String,
                        val typeOfFood: Any,
                        val unitDescription: Any,
                        val unitName: String,
                        val updatedAt: String
                    )
                }
            }

            data class OrganisationDetail(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val createdAt: String,
                        val name: String,
                        val updatedAt: String
                    )
                }
            }

            data class TemperatureSheetTemplates(
                val `data`: List<Data>
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val createdAt: String,
                        val name: String,
                        val updatedAt: String
                    )
                }
            }

            data class TypeOfBusiness(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val Name: String,
                        val createdAt: String,
                        val updatedAt: String
                    )
                }
            }

            data class Users(
                val `data`: List<Data>
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val allowChecks: Any,
                        val allowCompanyDetails: Any,
                        val allowHNSUnits: Any,
                        val allowSalesTracker: Any,
                        val allowTemperature: Any,
                        val allowToDo: Any,
                        val blocked: Boolean,
                        val confirmed: Boolean,
                        val createdAt: String,
                        val email: String,
                        val firstName: String,
                        val isAdmin: Any,
                        val lastName: String,
                        val mobile: String,
                        val otpConfirmed: Any,
                        val provider: String,
                        val telephone: Any,
                        val updatedAt: String,
                        val username: String
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