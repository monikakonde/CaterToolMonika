package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.Delete_User_Brand
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteUserBrandRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doDelete_User_Brand(auth:String,Id: String): MutableLiveData<Delete_User_Brand> {
        var apiResponse: MutableLiveData<Delete_User_Brand> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<Delete_User_Brand> = apiService.deleteUserBrandRequest(auth, Id)

        call.enqueue(object : Callback<Delete_User_Brand> {

            override fun onResponse(
                call: Call<Delete_User_Brand>,
                response: Response<Delete_User_Brand>
            ) {
                Log.e(TAG, "onResponse response Delete_User_Brand=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<Delete_User_Brand>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}