package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.GetAllToDoListToday
import com.example.catertool.viewmodel.repository.GetApiTodoTodayListRepository

class GetApiTodoTodayListModel:ViewModel() {
   private var getApiTodoTodayListRepository:GetApiTodoTodayListRepository = GetApiTodoTodayListRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<GetAllToDoListToday>? = null

    fun performGetTodoTodayList(auth:String,assigned_id:String,deadline_time: String,deadline_date: String,ststus: String,populate: String): MutableLiveData<GetAllToDoListToday>{
            mutableLiveDataGetBrandDetails = getApiTodoTodayListRepository.doGetTodoListToday(auth,assigned_id,deadline_time,deadline_date,ststus,populate)
        return mutableLiveDataGetBrandDetails as MutableLiveData<GetAllToDoListToday>
    }
}