package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.GetAddress
import com.example.catertool.viewmodel.repository.GetAddresRepository

class GetAddressViewModel:ViewModel() {
   private var getAddresRepository:GetAddresRepository = GetAddresRepository()
    var mutableLiveDataGetAddress: MutableLiveData<GetAddress>? = null

    fun performGetAddress(auth:String,Key:String, Id:String): MutableLiveData<GetAddress>{
            mutableLiveDataGetAddress = getAddresRepository.doGetAddress(auth,Key, Id)
        return mutableLiveDataGetAddress as MutableLiveData<GetAddress>
    }
}