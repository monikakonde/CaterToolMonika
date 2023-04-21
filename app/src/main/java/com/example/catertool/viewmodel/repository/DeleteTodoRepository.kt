package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.DeleteTodo
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteTodoRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doDeleteTodo(auth:String,Id: String): MutableLiveData<DeleteTodo> {
        var apiResponse: MutableLiveData<DeleteTodo> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<DeleteTodo> = apiService.deleteTodoRequest(auth, Id)

        call.enqueue(object : Callback<DeleteTodo> {

            override fun onResponse(
                call: Call<DeleteTodo>,
                response: Response<DeleteTodo>
            ) {
                Log.e(TAG, "onResponse response DeleteTodo=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<DeleteTodo>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}