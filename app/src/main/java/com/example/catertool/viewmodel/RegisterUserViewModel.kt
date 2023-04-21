package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.RegisterUser
import com.example.catertool.viewmodel.repository.RegisterUserRepository

class RegisterUserViewModel(private val registerUserRepository: RegisterUserRepository):ViewModel() {
    var mutableLiveDataLogin: MutableLiveData<RegisterUser>? = null

    fun performUserRegister(username:String, email:String,password:String, confirmed:String,blocked:String,firstName:String,lastName:String,mobile:String): MutableLiveData<RegisterUser>{
            mutableLiveDataLogin = registerUserRepository.performUserRegister(username,email,password,confirmed,blocked,firstName,lastName,mobile)
        return mutableLiveDataLogin as MutableLiveData<RegisterUser>
    }
}