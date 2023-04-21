package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.AllCommenOpningCheck
import com.example.catertool.model.GetBrandDetails
import com.example.catertool.viewmodel.repository.GetApiAllOpningCheckRepository

class GetApiAllCommenOpningCheckModel:ViewModel() {
   private var getApiAllOpningCheckRepository:GetApiAllOpningCheckRepository = GetApiAllOpningCheckRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<AllCommenOpningCheck>? = null

    fun performGetApiAllCommonPoningCheck(auth:String, populate:String,filter:String,isopning:String): MutableLiveData<AllCommenOpningCheck>{
            mutableLiveDataGetBrandDetails = getApiAllOpningCheckRepository.doGetApiAllCommonOpenCheck(auth,populate,filter,isopning)
        return mutableLiveDataGetBrandDetails as MutableLiveData<AllCommenOpningCheck>
    }
}