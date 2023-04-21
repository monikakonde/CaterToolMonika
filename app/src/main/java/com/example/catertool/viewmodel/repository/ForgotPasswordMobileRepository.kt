package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.ForgotPasswordMobile
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordMobileRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doForgotPasswordMobile(mobilenumber:String): MutableLiveData<ForgotPasswordMobile> {
        var apiResponse: MutableLiveData<ForgotPasswordMobile> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.forgotPasswordMobile(mobilenumber)

        val call: Call<ForgotPasswordMobile> = apiService.forgotPasswordMobileReqest(request)

        call.enqueue(object: Callback<ForgotPasswordMobile> {

            override fun onResponse(call: Call<ForgotPasswordMobile>, response: Response<ForgotPasswordMobile>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<ForgotPasswordMobile>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}