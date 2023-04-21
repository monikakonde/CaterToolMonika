package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.UpdatePostalAddressReqest
import com.example.catertool.model.UpdatePostalAddress
import com.example.catertool.viewmodel.repository.UpdatePostalAddressRepository

class UpdatePostalAddressViewModel:ViewModel() {
   private var updatePostalAddressRepository:UpdatePostalAddressRepository = UpdatePostalAddressRepository()
    var mutableLiveDataUpdatePostalAddress: MutableLiveData<UpdatePostalAddress>? = null

    fun performUpdatePostalAddress(identifierNo:String, id:String,body: UpdatePostalAddressReqest): MutableLiveData<UpdatePostalAddress>{
        if (mutableLiveDataUpdatePostalAddress == null) {
            mutableLiveDataUpdatePostalAddress = updatePostalAddressRepository.doUpdatePostalAddress(identifierNo,id,body)
        }
        return mutableLiveDataUpdatePostalAddress as MutableLiveData<UpdatePostalAddress>
    }
}