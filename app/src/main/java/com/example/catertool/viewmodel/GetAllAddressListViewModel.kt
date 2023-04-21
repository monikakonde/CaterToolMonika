package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.GetAddressList
import com.example.catertool.viewmodel.repository.GetAllAddresListRepository

class GetAllAddressListViewModel:ViewModel() {
   private var getAllAddresListRepository:GetAllAddresListRepository = GetAllAddresListRepository()
    var mutableLiveDataGetAddressList: MutableLiveData<GetAddressList>? = null

    fun performGetAddressList(auth:String,Key:String, Text:String): MutableLiveData<GetAddressList>{
            mutableLiveDataGetAddressList = getAllAddresListRepository.doGetAddressList(auth,Key, Text)
        return mutableLiveDataGetAddressList as MutableLiveData<GetAddressList>
    }
}