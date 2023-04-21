package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.UpdateDoneTodoReqest
import com.example.catertool.model.TodoDone
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateTodoDoneRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doDoneTodo(identifierNo:String,id:String,body: UpdateDoneTodoReqest): MutableLiveData<TodoDone> {
        var apiResponse: MutableLiveData<TodoDone> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<TodoDone> = apiService.putDoneTodoRequest(identifierNo,id,body)

        call.enqueue(object: Callback<TodoDone> {

            override fun onResponse(call: Call<TodoDone>, response: Response<TodoDone>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<TodoDone>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}