package com.example.catertool.model

data class HnsUnits(
    val `data`: List<Data>,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val allergen_matrices: AllergenMatrices,
            val brand_detail: BrandDetail,
            val certificates: Certificates,
            val createdAt: String,
            val foodImages: FoodImages,
            val menus: Menus,
            val typeOfFood: String,
            val unitDescription: String,
            val unitImage: UnitImage,
            val unitName: String,
            val unitType: UnitType,
            val updatedAt: String
        ) {
            data class AllergenMatrices(
                val `data`: List<Any>
            )

            data class BrandDetail(
                val `data`: Data
            ) {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) {
                    data class Attributes(
                        val addressLine1: String,
                        val addressLine2: String,
                        val businessEmail: String,
                        val companyName: String,
                        val county: String,
                        val createdAt: String,
                        val postcode: String,
                        val town: String,
                        val tradingName: String,
                        val updatedAt: String,
                        val vat_no: Any
                    )
                }
            }

            data class Certificates(
                val `data`: List<Any>
            )

            data class FoodImages(
                val `data`: List<Data>
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
                            val large: Large,
                            val medium: Medium,
                            val small: Small,
                            val thumbnail: Thumbnail
                        ) {
                            data class Large(
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

                            data class Medium(
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

                            data class Small(
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

            data class Menus(
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

            data class UnitImage(
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

            data class UnitType(
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