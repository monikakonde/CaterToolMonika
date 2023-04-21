package com.example.catertool.model

import iostudio.`in`.matrimonial.model.CommonResponse

data class Login( override var status_code: String,
                  override var message: String,
                  var jwt: String?,
                  var user: User?,
                  var brand_details: Brand_details?,
                  var error: ErrorData
) : CommonResponse()

data class ErrorData (
var status:String?,
var name: String?,
var message: String?,
)

data class User (
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
data class Brand_details (
    var id: String?,
    var companyName: String?,
    var companyLogo: String?,
    var noOfUsers: String?)