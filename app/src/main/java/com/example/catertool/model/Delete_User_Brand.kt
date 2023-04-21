package com.example.catertool.model

data class Delete_User_Brand(
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
    val id: Int,
    val isAdmin: Boolean,
    val lastName: String,
    val mobile: String,
    val otpConfirmed: Boolean,
    val provider: String,
    val telephone: Any,
    val updatedAt: String,
    val username: String
)