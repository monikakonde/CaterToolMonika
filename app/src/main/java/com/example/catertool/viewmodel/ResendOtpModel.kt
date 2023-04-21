package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.ResendOtp
import com.example.catertool.model.VerifyOtp
import com.example.catertool.viewmodel.repository.ResendOtpRepository

class ResendOtpModel:ViewModel() {
   private var resendOtpRepository:ResendOtpRepository = ResendOtpRepository()
    var mutableLiveResendOtp: MutableLiveData<ResendOtp>? = null

    fun performLogin(mobilenumber:String): MutableLiveData<ResendOtp>{
            mutableLiveResendOtp = resendOtpRepository.doResendOtp(mobilenumber)
        return mutableLiveResendOtp as MutableLiveData<ResendOtp>
    }
}