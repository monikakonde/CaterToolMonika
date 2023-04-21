package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.BusinessRegister
import com.example.catertool.network.RetrofitRequest
import com.google.gson.Gson
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterBusinessRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun performBusinessRegister(
        username: String,
        companyName: String,
        tradingName: String,
        postcode: String,
        addressLine1: String,
        country: String,
        businessType: String,
        businessEmail: String,
        typeOfBusiness: String
    ): MutableLiveData<BusinessRegister> {
        var apiResponse: MutableLiveData<BusinessRegister> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.registrationBusiness(username,companyName,tradingName,postcode,addressLine1,country,businessType,businessEmail,typeOfBusiness)

        val call: Call<BusinessRegister> = apiService.businessRegisterRequest(request)

        call.enqueue(object: Callback<BusinessRegister> {

            override fun onResponse(call: Call<BusinessRegister>, response: Response<BusinessRegister>) {
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

            override fun onFailure(call: Call<BusinessRegister>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
                Log.d("onResponse", "onFailure: ${t.message}")
                t.message
            }

        })

        return apiResponse
    }
}