package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.DeleteCommonCheck
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteCommonChecksRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doDeleteCommonCheck(auth:String,Id: String): MutableLiveData<DeleteCommonCheck> {
        var apiResponse: MutableLiveData<DeleteCommonCheck> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<DeleteCommonCheck> = apiService.deleteCommonCheckRequest(auth, Id)

        call.enqueue(object : Callback<DeleteCommonCheck> {

            override fun onResponse(
                call: Call<DeleteCommonCheck>,
                response: Response<DeleteCommonCheck>
            ) {
                Log.e(TAG, "onResponse response DeleteCommonCheck=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<DeleteCommonCheck>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}