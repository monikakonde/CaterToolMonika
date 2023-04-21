package com.example.catertool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catertool.model.Login
import com.example.catertool.model.BusinessTypes
import com.example.catertool.viewmodel.repository.BusinessTypesRepository
import com.example.catertool.viewmodel.repository.CountryListRepository


class CountryListViewModel : ViewModel() {
    private var countryListRepository: CountryListRepository = CountryListRepository()
    var mutableLiveDataBusinessType: MutableLiveData<BusinessTypes>? = null

    fun performCountryList(): MutableLiveData<BusinessTypes> {
        if (mutableLiveDataBusinessType == null) {
            mutableLiveDataBusinessType = countryListRepository.getCountryList()
        }
        return mutableLiveDataBusinessType as MutableLiveData<BusinessTypes>
    }
}