package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Delete_User_Brand
import com.example.catertool.viewmodel.repository.DeleteUserBrandRepository

class DeleteUserBrandViewModel:ViewModel() {
   private var DeleteUserBrandRepository:DeleteUserBrandRepository = DeleteUserBrandRepository()
    var mutableLiveDataDelete_User_Brand: MutableLiveData<Delete_User_Brand>? = null

    fun performDelete_User_Brand(auth:String,Id:String): MutableLiveData<Delete_User_Brand>{
            mutableLiveDataDelete_User_Brand = DeleteUserBrandRepository.doDelete_User_Brand(auth,Id)
        return mutableLiveDataDelete_User_Brand as MutableLiveData<Delete_User_Brand>
    }
}