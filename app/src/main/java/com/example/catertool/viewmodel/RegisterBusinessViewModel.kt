package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.BusinessRegister
import com.example.catertool.viewmodel.repository.RegisterBusinessRepository

class RegisterBusinessViewModel:ViewModel() {
   private var registerBusinessRepository: RegisterBusinessRepository = RegisterBusinessRepository()
    var mutableLiveDataLoginRegisterBusiness: MutableLiveData<BusinessRegister>? = null

    fun performBusinessRegister(username:String, companyName:String,tradingName:String, postcode:String,addressLine1:String,country:String,businessType:String,businessEmail:String,typeOfBusiness:String): MutableLiveData<BusinessRegister>{
        if (mutableLiveDataLoginRegisterBusiness == null) {
            mutableLiveDataLoginRegisterBusiness = registerBusinessRepository.performBusinessRegister(username,companyName,tradingName,postcode,addressLine1,country,businessType,businessEmail,typeOfBusiness)
        }
        return mutableLiveDataLoginRegisterBusiness as MutableLiveData<BusinessRegister>
    }
}