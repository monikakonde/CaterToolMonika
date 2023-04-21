package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.AddToDoReqest
import com.example.catertool.model.AddTodo
import com.example.catertool.viewmodel.repository.AddToDoRepository

class AddTodoViewModel:ViewModel() {
   private var addToDoRepository:AddToDoRepository = AddToDoRepository()
    var mutableLiveDataAddTodo: MutableLiveData<AddTodo>? = null

    fun performAddToTO(identifierNo:String,body: AddToDoReqest): MutableLiveData<AddTodo>{
            mutableLiveDataAddTodo = addToDoRepository.doAddToDo(identifierNo,body)
        return mutableLiveDataAddTodo as MutableLiveData<AddTodo>
    }
}