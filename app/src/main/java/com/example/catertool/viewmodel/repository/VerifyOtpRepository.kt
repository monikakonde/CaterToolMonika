package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.VerifyOtp
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifyOtpRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doOtpVerify(mobilenumber:String, otp:String): MutableLiveData<VerifyOtp> {
        var apiResponse: MutableLiveData<VerifyOtp> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.registverifyOtp(mobilenumber, otp)

        val call: Call<VerifyOtp> = apiService.verifyOtpRequest(request)

        call.enqueue(object: Callback<VerifyOtp> {

            override fun onResponse(call: Call<VerifyOtp>, response: Response<VerifyOtp>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<VerifyOtp>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}