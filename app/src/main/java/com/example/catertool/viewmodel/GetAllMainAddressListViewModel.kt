package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.MainAddressList
import com.example.catertool.viewmodel.repository.GetAllMainAddresListRepository

class GetAllMainAddressListViewModel:ViewModel() {
   private var getAllMainAddresListRepository:GetAllMainAddresListRepository = GetAllMainAddresListRepository()
    var mutableLiveDataMainAddressList: MutableLiveData<MainAddressList>? = null

    fun performMainAddressList(auth:String,Key:String, Text:String): MutableLiveData<MainAddressList>{
            mutableLiveDataMainAddressList = getAllMainAddresListRepository.doMainAddressList(auth,Key, Text)
        return mutableLiveDataMainAddressList as MutableLiveData<MainAddressList>
    }
}