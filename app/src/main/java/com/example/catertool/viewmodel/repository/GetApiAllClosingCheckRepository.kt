package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.AllCommenOpningCheck
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiAllClosingCheckRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetApiAllCommoncClosingCheck(auth:String,populate: String,filter: String,isClosing:String): MutableLiveData<AllCommenOpningCheck> {
        var apiResponse: MutableLiveData<AllCommenOpningCheck> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getApiAllClosingCheckReqest(populate, filter,isClosing)

        val call: Call<AllCommenOpningCheck> = apiService.getApiGetOpeningChecks(
            auth,
            request)
        call.enqueue(object : Callback<AllCommenOpningCheck> {
            override fun onResponse(
                call: Call<AllCommenOpningCheck>,
                response: Response<AllCommenOpningCheck>
            ) {
                Log.e(TAG, "onResponse response AllCommenOpningCheck=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }
            override fun onFailure(call: Call<AllCommenOpningCheck>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }
        })
        return apiResponse
    }
}