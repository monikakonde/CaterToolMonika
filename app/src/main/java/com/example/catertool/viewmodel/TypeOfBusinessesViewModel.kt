package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.TypeOfBusinesses
import com.example.catertool.viewmodel.repository.TypeOfBusinessesRepository


class TypeOfBusinessesViewModel : ViewModel() {
    private var TypeOfBusinessesRepository: TypeOfBusinessesRepository =
        TypeOfBusinessesRepository()
    var mutableLiveDataLogin: MutableLiveData<TypeOfBusinesses>? = null

    fun performTypeOfBusinesses(): MutableLiveData<TypeOfBusinesses> {
        if (mutableLiveDataLogin == null) {
            mutableLiveDataLogin = TypeOfBusinessesRepository.getTypeOfBusinesses()
        }
        return mutableLiveDataLogin as MutableLiveData<TypeOfBusinesses>
    }
}