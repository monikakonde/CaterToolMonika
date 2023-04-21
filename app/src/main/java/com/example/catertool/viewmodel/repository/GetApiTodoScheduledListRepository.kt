package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.GetAllTodoScheduled
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiTodoScheduledListRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetShduledToday(auth:String,assigned_id:String,deadline_time: String,ststus: String,populate: String): MutableLiveData<GetAllTodoScheduled> {
        var apiResponse: MutableLiveData<GetAllTodoScheduled> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getApiTodoListScheduledReqest(assigned_id,deadline_time,ststus,populate)

        val call: Call<GetAllTodoScheduled> = apiService.GetApiGetAllToDoScheduled(auth,request)
        call.enqueue(object : Callback<GetAllTodoScheduled> {
            override fun onResponse(
                call: Call<GetAllTodoScheduled>,
                response: Response<GetAllTodoScheduled>
            ) {
                Log.e(TAG, "onResponse response GetAllTodoScheduledd=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.sized=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }
            override fun onFailure(call: Call<GetAllTodoScheduled>, t: Throwable) {
                Log.e(TAG, "onFailure calld=" + call.toString())
            }
        })
        return apiResponse
    }
}