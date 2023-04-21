package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.DeleteTodo
import com.example.catertool.viewmodel.repository.DeleteTodoRepository

class DeleteTodoViewModel:ViewModel() {
   private var deleteTodoRepository:DeleteTodoRepository = DeleteTodoRepository()
    var mutableLiveDataDeleteTodo: MutableLiveData<DeleteTodo>? = null

    fun performDeleteTodo(auth:String,Id:String): MutableLiveData<DeleteTodo>{
            mutableLiveDataDeleteTodo = deleteTodoRepository.doDeleteTodo(auth,Id)
        return mutableLiveDataDeleteTodo as MutableLiveData<DeleteTodo>
    }
}