package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.TodoComplited
import com.example.catertool.viewmodel.repository.GetApiTodoComplitedListRepository

class GetApiTodoComplitedListModel:ViewModel() {
   private var getApiTodoComplitedListRepository:GetApiTodoComplitedListRepository = GetApiTodoComplitedListRepository()
    var mutableLiveDataGetBrandDetails: MutableLiveData<TodoComplited>? = null

    fun performGetComplitedList(auth:String,assigned_id:String,deadline_time: String,ststus: String,populate: String): MutableLiveData<TodoComplited>{
            mutableLiveDataGetBrandDetails = getApiTodoComplitedListRepository.doGetTodoListComplited(auth,assigned_id,deadline_time,ststus,populate)
        return mutableLiveDataGetBrandDetails as MutableLiveData<TodoComplited>
    }
}