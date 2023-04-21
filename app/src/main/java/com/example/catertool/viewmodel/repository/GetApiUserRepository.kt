package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.GetAllUserOfBrand
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiUserRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetApiUserBrand(auth:String,populate: String,filter: String): MutableLiveData<GetAllUserOfBrand> {
        var apiResponse: MutableLiveData<GetAllUserOfBrand> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getApiUserReqest(populate, filter)

        val call: Call<GetAllUserOfBrand> = apiService.getApiUsersReqest(
            auth,
            request
        )
        Log.e("hfbeifbqwf", "onResponse response GetAllUserOfBrand url=$populate,$filter")
        call.enqueue(object : Callback<GetAllUserOfBrand> {

            override fun onResponse(
                call: Call<GetAllUserOfBrand>,
                response: Response<GetAllUserOfBrand>
            ) {
                Log.e(TAG, "onResponse response GetAllUserOfBrand=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<GetAllUserOfBrand>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}