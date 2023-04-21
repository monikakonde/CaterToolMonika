package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.AllCommenOpningCheck
import com.example.catertool.model.GetBrandDetails
import com.example.catertool.viewmodel.repository.GetApiAllClosingCheckRepository

class GetApiAllCommenClosingCheckModel:ViewModel() {
   private var getApiAllClosingCheckRepository:GetApiAllClosingCheckRepository = GetApiAllClosingCheckRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<AllCommenOpningCheck>? = null

    fun performGetApiAllCommonConingCheck(auth:String, populate:String,filter:String,isClosing:String): MutableLiveData<AllCommenOpningCheck>{
            mutableLiveDataGetBrandDetails = getApiAllClosingCheckRepository.doGetApiAllCommoncClosingCheck(auth,populate,filter,isClosing)
        return mutableLiveDataGetBrandDetails as MutableLiveData<AllCommenOpningCheck>
    }
}