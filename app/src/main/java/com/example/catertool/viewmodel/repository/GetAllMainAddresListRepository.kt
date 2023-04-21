package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.MainAddressList
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.util.Constants
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllMainAddresListRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doMainAddressList(auth:String,Key: String, Text: String): MutableLiveData<MainAddressList> {
        var apiResponse: MutableLiveData<MainAddressList> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getMainAddressListReqest(Key, Text)

        val call: Call<MainAddressList> = apiService.getMainAddressListInterface(Constants.API.GET_MAIN_ADDRESS_LIST,auth, request)

        call.enqueue(object : Callback<MainAddressList> {

            override fun onResponse(
                call: Call<MainAddressList>,
                response: Response<MainAddressList>
            ) {
                Log.e(TAG, "onResponse response MainAddressList=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<MainAddressList>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}