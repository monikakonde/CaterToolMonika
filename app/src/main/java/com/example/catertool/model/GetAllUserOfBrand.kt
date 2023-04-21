package com.example.catertool.model

class GetAllUserOfBrand : ArrayList<GetAllUserOfBrand.getAllUserOfBrandItem>(){
    data class getAllUserOfBrandItem(
        val allowChecks: Boolean,
        val allowCompanyDetails: Boolean,
        val allowHNSUnits: Boolean,
        val allowSalesTracker: Boolean,
        val allowTemperature: Boolean,
        val allowToDo: Boolean,
        val blocked: Boolean,
        val brand_details: List<BrandDetail>,
        val confirmed: Boolean,
        val createdAt: String,
        val email: String,
        val event_todo_items: List<EventTodoItem>,
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
    ) {
        data class BrandDetail(
            val addressLine1: String,
            val addressLine2: String,
            val businessEmail: String,
            val companyName: String,
            val county: String,
            val createdAt: String,
            val id: Int,
            val postcode: String,
            val town: String,
            val tradingName: String,
            val updatedAt: String,
            val vat_no: Any
        )
    
        data class EventTodoItem(
            val createdAt: String,
            val deadlineDateTime: String,
            val id: Int,
            val notes: String,
            val status: String,
            val title: String,
            val updatedAt: String
        )
    }
}