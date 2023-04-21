package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.viewmodel.repository.LoginRepository

class LoginViewModel(private val loginRepository:LoginRepository):ViewModel() {
    var mutableLiveDataLogin: MutableLiveData<Login>? = null

    fun performLogin(identifierNo:String, password:String): MutableLiveData<Login>{
      //  if (mutableLiveDataLogin == null) {
            mutableLiveDataLogin = loginRepository.doLogin(identifierNo, password)
      //  }
        return mutableLiveDataLogin as MutableLiveData<Login>
    }
}