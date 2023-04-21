package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.UseMe
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UseMeRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doUseMe(Auth:String): MutableLiveData<UseMe> {
        var apiResponse: MutableLiveData<UseMe> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<UseMe> = apiService.usersMeReqest("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiaWF0IjoxNjc1MjQ2ODI3LCJleHAiOjE2Nzc4Mzg4Mjd9.ygR_czjSjZlAwrYFo8_kbIMZ-Sm5bkfeRiCX2FlvGqA")

        call.enqueue(object: Callback<UseMe> {

            override fun onResponse(call: Call<UseMe>, response: Response<UseMe>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<UseMe>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}