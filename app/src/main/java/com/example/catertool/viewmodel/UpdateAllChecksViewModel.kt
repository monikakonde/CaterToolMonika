package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.AddChecksReqest
import com.example.catertool.model.UpdateChecks
import com.example.catertool.viewmodel.repository.UpdateAllCheckDeatilsRepository

class UpdateAllChecksViewModel:ViewModel() {
   private var updateAllCheckDeatilsRepository:UpdateAllCheckDeatilsRepository = UpdateAllCheckDeatilsRepository()
    var mutableLiveDataUpdateChecks: MutableLiveData<UpdateChecks>? = null

    fun performUpdateAllChecks(identifierNo:String,body: AddChecksReqest): MutableLiveData<UpdateChecks>{
            mutableLiveDataUpdateChecks = updateAllCheckDeatilsRepository.doUpdateallchecks(identifierNo,body)
        return mutableLiveDataUpdateChecks as MutableLiveData<UpdateChecks>
    }
}