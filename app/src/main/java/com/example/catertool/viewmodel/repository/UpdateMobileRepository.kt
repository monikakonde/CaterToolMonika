package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.MobileNumberUpdateRequest
import com.example.catertool.model.UpdateEmail
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateMobileRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doUpdateMobile(identifierNo:String,id:String,body: MobileNumberUpdateRequest): MutableLiveData<UpdateEmail> {
        var apiResponse: MutableLiveData<UpdateEmail> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<UpdateEmail> = apiService.putMobileRequest(identifierNo,id,body)

        call.enqueue(object: Callback<UpdateEmail> {

            override fun onResponse(call: Call<UpdateEmail>, response: Response<UpdateEmail>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<UpdateEmail>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}