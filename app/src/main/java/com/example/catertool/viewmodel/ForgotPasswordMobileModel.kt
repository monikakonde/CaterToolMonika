package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.ForgotPasswordMobile
import com.example.catertool.viewmodel.repository.ForgotPasswordMobileRepository

class ForgotPasswordMobileModel:ViewModel() {
   private var forgotPasswordMobileRepository:ForgotPasswordMobileRepository = ForgotPasswordMobileRepository()
    var mutableLiveForgotPasswordMobile: MutableLiveData<ForgotPasswordMobile>? = null

    fun performForgotPasswordMobile(mobilenumber:String): MutableLiveData<ForgotPasswordMobile>{
            mutableLiveForgotPasswordMobile = forgotPasswordMobileRepository.doForgotPasswordMobile(mobilenumber)
        return mutableLiveForgotPasswordMobile as MutableLiveData<ForgotPasswordMobile>
    }
}