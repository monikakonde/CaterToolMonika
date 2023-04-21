package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.BrandDetailsRequest
import com.example.catertool.model.EmailUpdateReqest
import com.example.catertool.model.MobileNumberUpdateRequest
import com.example.catertool.model.UpdateEmail
import com.example.catertool.viewmodel.repository.UpdateMobileRepository

class UpdateMobileViewModel:ViewModel() {
   private var updateMobileRepository:UpdateMobileRepository = UpdateMobileRepository()
    var mutableLiveDataUpdateEmail: MutableLiveData<UpdateEmail>? = null

    fun performUpdateMobile(identifierNo:String,id:String,body: MobileNumberUpdateRequest): MutableLiveData<UpdateEmail>{
        if (mutableLiveDataUpdateEmail == null) {
            mutableLiveDataUpdateEmail = updateMobileRepository.doUpdateMobile(identifierNo,id,body)
        }
        return mutableLiveDataUpdateEmail as MutableLiveData<UpdateEmail>
    }
}