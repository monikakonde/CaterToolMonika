package com.example.catertool.model

data class UpdateEmail(
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
    val id: Int,
    val isAdmin: Any,
    val lastName: String,
    val mobile: String,
    val otpConfirmed: Boolean,
    val provider: String,
    val telephone: Any,
    val updatedAt: String,
    val username: String
)