package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.GetAddress
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.util.Constants.API.GET_ADDRESS
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAddresRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetAddress(auth:String,Key: String, Id: String): MutableLiveData<GetAddress> {
        var apiResponse: MutableLiveData<GetAddress> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getAddressReqest(Key, Id)

        val call: Call<GetAddress> = apiService.getAddressInterface(GET_ADDRESS,auth, request)

        call.enqueue(object : Callback<GetAddress> {

            override fun onResponse(
                call: Call<GetAddress>,
                response: Response<GetAddress>
            ) {
                Log.e(TAG, "onResponse response GetAddress=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<GetAddress>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}