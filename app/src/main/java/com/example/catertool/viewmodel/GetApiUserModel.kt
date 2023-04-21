package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.GetAllUserOfBrand
import com.example.catertool.model.GetBrandDetails
import com.example.catertool.viewmodel.repository.GetApiUserRepository

class GetApiUserModel:ViewModel() {
   private var getApiUserRepository:GetApiUserRepository = GetApiUserRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<GetAllUserOfBrand>? = null

    fun performGetApiUserList(auth:String, populate:String,filter:String,): MutableLiveData<GetAllUserOfBrand>{
        if (mutableLiveDataGetBrandDetails == null) {
            mutableLiveDataGetBrandDetails = getApiUserRepository.doGetApiUserBrand(auth,populate, filter)
        }
        return mutableLiveDataGetBrandDetails as MutableLiveData<GetAllUserOfBrand>
    }
}