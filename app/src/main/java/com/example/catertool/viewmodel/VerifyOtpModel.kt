package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.VerifyOtp
import com.example.catertool.viewmodel.repository.VerifyOtpRepository

class VerifyOtpModel:ViewModel() {
   private var VerifyOtpRepository:VerifyOtpRepository = VerifyOtpRepository()
    var mutableLiveVerifyOtp: MutableLiveData<VerifyOtp>? = null

    fun performLogin(mobilenumber:String, otp:String): MutableLiveData<VerifyOtp>{
            mutableLiveVerifyOtp = VerifyOtpRepository.doOtpVerify(mobilenumber, otp)
        return mutableLiveVerifyOtp as MutableLiveData<VerifyOtp>
    }
}