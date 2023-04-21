package com.example.catertool.model

import iostudio.`in`.matrimonial.model.CommonResponse

data class TypeOfBusinesses(
    override var status_code: String,
    override var message: String,
    var data: List<Data>?,
    var error: ErrorData1
) : CommonResponse()


data class Data(
    var id: String?,
    var attributes: Attributes?,
)

data class Attributes(
    var Name: String?,
    var createdAt: String?,
    var updatedAt: String?,
)


