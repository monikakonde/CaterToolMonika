package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.GetAllTodoScheduled
import com.example.catertool.viewmodel.repository.GetApiTodoScheduledListRepository

class GetApiTodoSchduledListModel:ViewModel() {
   private var getApiTodoScheduledListRepository:GetApiTodoScheduledListRepository = GetApiTodoScheduledListRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<GetAllTodoScheduled>? = null

    fun performGetSchduledList(auth:String,assigned_id:String,deadline_time: String,ststus: String,populate: String): MutableLiveData<GetAllTodoScheduled>{
            mutableLiveDataGetBrandDetails = getApiTodoScheduledListRepository.doGetShduledToday(auth,assigned_id,deadline_time,ststus,populate)
        return mutableLiveDataGetBrandDetails as MutableLiveData<GetAllTodoScheduled>
    }
}