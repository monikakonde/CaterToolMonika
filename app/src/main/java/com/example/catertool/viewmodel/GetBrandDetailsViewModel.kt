package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.GetBrandDetails
import com.example.catertool.viewmodel.repository.GetBrandDetailsRepository

class GetBrandDetailsViewModel:ViewModel() {
   private var getBrandDetailsRepository:GetBrandDetailsRepository = GetBrandDetailsRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<GetBrandDetails>? = null

    fun performGetBrandDetails(auth:String,filter:String, populate:String): MutableLiveData<GetBrandDetails>{
        if (mutableLiveDataGetBrandDetails == null) {
            mutableLiveDataGetBrandDetails = getBrandDetailsRepository.doGetBrandDetails(auth,filter, populate)
        }
        return mutableLiveDataGetBrandDetails as MutableLiveData<GetBrandDetails>
    }
}