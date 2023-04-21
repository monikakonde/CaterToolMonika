package com.example.catertool.model

import iostudio.`in`.matrimonial.model.CommonResponse

data class  VerifyOtp( override var status_code: String,
                  override var message: String,
                  var jwt: String?,
                  var user: User3?,
                  var error: ErrorData3
) : CommonResponse()

data class ErrorData3 (
var status:String?,
var name: String?,
var message: String?,
)

data class User3 (
    var id: String?,
    var username: String?,
    var email: String?,
    var provider: String?,
    var confirmed: String?,
    var blocked: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var firstName: String?,
    var lastName: String?,
    var telephone: String?,
    var mobile: String?,
    var otpConfirmed: String?
)