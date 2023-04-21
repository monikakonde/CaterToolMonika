package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.EventSelaseRecordList
import com.example.catertool.viewmodel.repository.GetApiSelaseEventListRepository

class GetApiSelaseRecordEventListModel:ViewModel() {
   private var getApiSelaseEventListRepository:GetApiSelaseEventListRepository = GetApiSelaseEventListRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<EventSelaseRecordList>? = null

    fun performGetSealseEventList(auth:String,populate:String,filter:String): MutableLiveData<EventSelaseRecordList>{
            mutableLiveDataGetBrandDetails = getApiSelaseEventListRepository.doGetSelaseEvent(auth,populate,filter)
        return mutableLiveDataGetBrandDetails as MutableLiveData<EventSelaseRecordList>
    }
}