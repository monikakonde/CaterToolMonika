package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.EventSelaseRecordList
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiSelaseEventListRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetSelaseEvent(auth:String,populate: String,filter: String): MutableLiveData<EventSelaseRecordList> {
        var apiResponse: MutableLiveData<EventSelaseRecordList> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getApiEventListReqest(populate,filter)

        val call: Call<EventSelaseRecordList> = apiService.GetApiSelaseRecordRepository(auth,request)
        call.enqueue(object : Callback<EventSelaseRecordList> {
            override fun onResponse(
                call: Call<EventSelaseRecordList>,
                response: Response<EventSelaseRecordList>
            ) {
                Log.e(TAG, "onResponse response EventSelaseRecordListd=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.sized=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }
            override fun onFailure(call: Call<EventSelaseRecordList>, t: Throwable) {
                Log.e(TAG, "onFailure calld=" + call.toString())
            }
        })
        return apiResponse
    }
}