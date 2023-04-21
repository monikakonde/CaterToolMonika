package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.BrandDetailsRequest
import com.example.catertool.model.UpdateBrandDetails
import com.example.catertool.viewmodel.repository.UpdateBrandDeatilsRepository

class UpdateBrandDetailsViewModel:ViewModel() {
   private var updateBrandDeatilsRepository:UpdateBrandDeatilsRepository = UpdateBrandDeatilsRepository()
    var mutableLiveDataUpdateBrandDetails: MutableLiveData<UpdateBrandDetails>? = null

    fun performUpdateBrand(identifierNo:String, id:String,body: BrandDetailsRequest): MutableLiveData<UpdateBrandDetails>{
        if (mutableLiveDataUpdateBrandDetails == null) {
            mutableLiveDataUpdateBrandDetails = updateBrandDeatilsRepository.doUpdateBrand(identifierNo,id,body)
        }
        return mutableLiveDataUpdateBrandDetails as MutableLiveData<UpdateBrandDetails>
    }
}