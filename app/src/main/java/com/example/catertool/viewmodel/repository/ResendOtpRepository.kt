package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.ResendOtp
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResendOtpRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doResendOtp(mobilenumber:String): MutableLiveData<ResendOtp> {
        var apiResponse: MutableLiveData<ResendOtp> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.resendOtp(mobilenumber)

        val call: Call<ResendOtp> = apiService.resendOtpRequest(request)

        call.enqueue(object: Callback<ResendOtp> {

            override fun onResponse(call: Call<ResendOtp>, response: Response<ResendOtp>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<ResendOtp>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}