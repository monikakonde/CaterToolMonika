package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.viewmodel.repository.ResetPasswordRepository

class ResetPasswordViewModel:ViewModel() {
   private var ResetPasswordRepository:ResetPasswordRepository = ResetPasswordRepository()
    var mutableLiveDataLogin: MutableLiveData<Login>? = null

    fun performResetPassword(Mobile:String, otp:String,password:String, passwordConfirmation:String): MutableLiveData<Login>{
            mutableLiveDataLogin = ResetPasswordRepository.doResetPassword(Mobile, otp,password,passwordConfirmation)
        return mutableLiveDataLogin as MutableLiveData<Login>
    }
}