package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.HnsUnits
import com.example.catertool.viewmodel.repository.GetApiHnsUnitRepository

class GetApiHnsUnitModel:ViewModel() {
   private var getApiHnsUnitRepository:GetApiHnsUnitRepository = GetApiHnsUnitRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<HnsUnits>? = null

    fun performGetApiHnsUnit(auth:String,filter:String,populate:String): MutableLiveData<HnsUnits>{
            mutableLiveDataGetBrandDetails = getApiHnsUnitRepository.doGetHnsUnit(auth,filter,populate)
        return mutableLiveDataGetBrandDetails as MutableLiveData<HnsUnits>
    }
}