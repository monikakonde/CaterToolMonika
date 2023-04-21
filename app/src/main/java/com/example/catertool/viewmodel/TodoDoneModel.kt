package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.BrandDetailsRequest
import com.example.catertool.model.EmailUpdateReqest
import com.example.catertool.model.UpdateDoneTodoReqest
import com.example.catertool.model.TodoDone
import com.example.catertool.viewmodel.repository.UpdateTodoDoneRepository

class TodoDoneModel:ViewModel() {
   private var updateTodoDoneRepository:UpdateTodoDoneRepository = UpdateTodoDoneRepository()
    var mutableLiveDataTodoDone: MutableLiveData<TodoDone>? = null

    fun performDoneTodo(identifierNo:String,id:String,body: UpdateDoneTodoReqest): MutableLiveData<TodoDone>{
        if (mutableLiveDataTodoDone == null) {
            mutableLiveDataTodoDone = updateTodoDoneRepository.doDoneTodo(identifierNo,id,body)
        }
        return mutableLiveDataTodoDone as MutableLiveData<TodoDone>
    }
}