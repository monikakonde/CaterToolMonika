package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.BusinessTypes
import com.google.gson.Gson
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryListRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun getCountryList(): MutableLiveData<BusinessTypes> {
        var apiResponse: MutableLiveData<BusinessTypes> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<BusinessTypes> = apiService.countryListRequest()

        call.enqueue(object: Callback<BusinessTypes> {

            override fun onResponse(call: Call<BusinessTypes>, response: Response<BusinessTypes>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }else{
                    Log.d("onResponse", "onResponse01: ${Gson().toJson(response)}")

                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<BusinessTypes>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
                Log.d("onResponse", "onFailure: ${t.message}")
                t.message
            }

        })

        return apiResponse
    }
}