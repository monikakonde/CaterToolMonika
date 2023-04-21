package com.example.catertool.model

import iostudio.`in`.matrimonial.model.CommonResponse

data class BusinessTypes(
    override var status_code: String,
    override var message: String,
    var data: List<Data1>?,
    var error: ErrorData1
) : CommonResponse()


data class Data1(
    var id: String?,
    var attributes: Attributes1?,
)

data class Attributes1(
    var Name: String?,
    var createdAt: String?,
    var updatedAt: String?,
)


