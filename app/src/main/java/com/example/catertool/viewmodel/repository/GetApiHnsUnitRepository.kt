package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.HnsUnits
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiHnsUnitRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetHnsUnit(auth:String,filter: String,populate: String): MutableLiveData<HnsUnits> {
        var apiResponse: MutableLiveData<HnsUnits> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getApiHnsUnitReqest(filter,populate)

        val call: Call<HnsUnits> = apiService.GetApiHnsUnitRepository(auth,request)
        call.enqueue(object : Callback<HnsUnits> {
            override fun onResponse(
                call: Call<HnsUnits>,
                response: Response<HnsUnits>
            ) {
                Log.e(TAG, "onResponse response HnsUnitsd=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.sized=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }
            override fun onFailure(call: Call<HnsUnits>, t: Throwable) {
                Log.e(TAG, "onFailure calld=" + call.toString())
            }
        })
        return apiResponse
    }
}