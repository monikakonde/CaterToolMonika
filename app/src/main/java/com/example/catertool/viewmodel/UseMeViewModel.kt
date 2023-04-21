package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.UseMe
import com.example.catertool.viewmodel.repository.LoginRepository
import com.example.catertool.viewmodel.repository.UseMeRepository

class UseMeViewModel:ViewModel() {
   private var useMeRepository: UseMeRepository = UseMeRepository()
    var mutableLiveDataUseMe: MutableLiveData<UseMe>? = null

    fun performUseMe(Auth:String): MutableLiveData<UseMe>{
        if (mutableLiveDataUseMe == null) {
            mutableLiveDataUseMe = useMeRepository.doUseMe(Auth)
        }
        return mutableLiveDataUseMe as MutableLiveData<UseMe>
    }
}