package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.GetAllToDoListToday
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiTodoTodayListRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetTodoListToday(auth:String,assigned_id:String,deadline_time: String,deadline_date: String,ststus: String,populate: String): MutableLiveData<GetAllToDoListToday> {
        var apiResponse: MutableLiveData<GetAllToDoListToday> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getApiTodoListTodayReqest(assigned_id,deadline_time,deadline_date,ststus,populate)

        val call: Call<GetAllToDoListToday> = apiService.GetApiGetAllToDoToday(auth,request)
        call.enqueue(object : Callback<GetAllToDoListToday> {
            override fun onResponse(
                call: Call<GetAllToDoListToday>,
                response: Response<GetAllToDoListToday>
            ) {
                Log.e(TAG, "onResponse response GetAllToDoListTodayd=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.sized=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }
            override fun onFailure(call: Call<GetAllToDoListToday>, t: Throwable) {
                Log.e(TAG, "onFailure calld=" + call.toString())
            }
        })
        return apiResponse
    }
}