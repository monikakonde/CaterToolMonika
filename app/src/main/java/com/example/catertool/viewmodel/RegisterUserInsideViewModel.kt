package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.RegisterUser
import com.example.catertool.viewmodel.repository.RegisterUserInsideRepository
import com.example.catertool.viewmodel.repository.RegisterUserRepository

class RegisterUserInsideViewModel:ViewModel() {
   private var registerUserInsideRepository: RegisterUserInsideRepository = RegisterUserInsideRepository()
    var mutableLiveDataLogin: MutableLiveData<RegisterUser>? = null

    fun performUserInsideRegister(username:String, email:String,password:String, confirmed:String,blocked:String,firstName:String,lastName:String,mobile:String,allowCompanyDetails:String,allowHNSUnits:String,allowTemperature:String,allowChecks:String,allowSalesTracker:String,allowToDo:String,brand_details:String): MutableLiveData<RegisterUser>{
            mutableLiveDataLogin = registerUserInsideRepository.performUserInsideRegister(username,email,password,confirmed,blocked,firstName,lastName,mobile,allowCompanyDetails,allowHNSUnits,allowTemperature,allowChecks,allowSalesTracker,allowToDo,brand_details)
        return mutableLiveDataLogin as MutableLiveData<RegisterUser>
    }
}