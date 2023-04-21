package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.BrandDetailsRequest
import com.example.catertool.model.EmailUpdateReqest
import com.example.catertool.model.UpdateEmail
import com.example.catertool.viewmodel.repository.UpdateEmailRepository

class UpdateEmailViewModel:ViewModel() {
   private var updateEmailRepository:UpdateEmailRepository = UpdateEmailRepository()
    var mutableLiveDataUpdateEmail: MutableLiveData<UpdateEmail>? = null

    fun performUpdateEmail(identifierNo:String,id:String,body: EmailUpdateReqest): MutableLiveData<UpdateEmail>{
        if (mutableLiveDataUpdateEmail == null) {
            mutableLiveDataUpdateEmail = updateEmailRepository.doUpdateEmail(identifierNo,id,body)
        }
        return mutableLiveDataUpdateEmail as MutableLiveData<UpdateEmail>
    }
}