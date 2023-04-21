package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.BusinessTypes
import com.example.catertool.viewmodel.repository.BusinessTypesRepository


class BusinessesTypeViewModel : ViewModel() {
    private var BusinessTypesRepository: BusinessTypesRepository =
        BusinessTypesRepository()
    var mutableLiveDataBusinessType: MutableLiveData<BusinessTypes>? = null

    fun performBusinessTypes(): MutableLiveData<BusinessTypes> {
        if (mutableLiveDataBusinessType == null) {
            mutableLiveDataBusinessType = BusinessTypesRepository.getBusinessTypes()
        }
        return mutableLiveDataBusinessType as MutableLiveData<BusinessTypes>
    }
}