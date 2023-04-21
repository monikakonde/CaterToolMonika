package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.TodoComplited
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiTodoComplitedListRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetTodoListComplited(auth:String,assigned_id:String,deadline_time: String,ststus: String,populate: String): MutableLiveData<TodoComplited> {
        var apiResponse: MutableLiveData<TodoComplited> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getApiTodoListCompliedReqest(assigned_id,deadline_time,ststus,populate)

        val call: Call<TodoComplited> = apiService.GetApiGetAllToComplited(auth,request)
        call.enqueue(object : Callback<TodoComplited> {
            override fun onResponse(
                call: Call<TodoComplited>,
                response: Response<TodoComplited>
            ) {
                Log.e(TAG, "onResponse response TodoComplitedd=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.sized=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }
            override fun onFailure(call: Call<TodoComplited>, t: Throwable) {
                Log.e(TAG, "onFailure calld=" + call.toString())
            }
        })
        return apiResponse
    }
}