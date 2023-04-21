package com.example.catertool.model

import iostudio.`in`.matrimonial.model.CommonResponse


data class RegisterUser(
    override var status_code: String,
    override var message: String,
    var jwt: String?,
    var user: User1?,
    var otp: otp1?,
    var error: ErrorData2
) : CommonResponse() {

}

data class ErrorData2(
    var status: String?,
    var name: String?,
    var message: String?,
)

data class otp1(
    var sent: Boolean?,
)

data class User1(
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
    var otpConfirmed: String?,
    var allowCompanyDetails: String?,
    var allowHNSUnits: String?,
    var allowTemperature: String?,
    var allowChecks: String?,
    var allowSalesTracker: String?,
    var allowToDo: String?,
    var isAdmin: String?,
)