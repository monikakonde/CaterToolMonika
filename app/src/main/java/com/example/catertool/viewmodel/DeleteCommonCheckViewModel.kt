package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.DeleteCommonCheck
import com.example.catertool.viewmodel.repository.DeleteCommonChecksRepository

class DeleteCommonCheckViewModel:ViewModel() {
   private var deleteCommonChecksRepository:DeleteCommonChecksRepository = DeleteCommonChecksRepository()
    var mutableLiveDataDeleteCommonCheck: MutableLiveData<DeleteCommonCheck>? = null

    fun performDeleteCommonCheck(auth:String,Id:String): MutableLiveData<DeleteCommonCheck>{
            mutableLiveDataDeleteCommonCheck = deleteCommonChecksRepository.doDeleteCommonCheck(auth,Id)
        return mutableLiveDataDeleteCommonCheck as MutableLiveData<DeleteCommonCheck>
    }
}