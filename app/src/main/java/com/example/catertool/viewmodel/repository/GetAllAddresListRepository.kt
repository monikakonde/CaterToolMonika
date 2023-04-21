package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.GetAddressList
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.util.Constants.API.GET_ADDRESS_LIST
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllAddresListRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetAddressList(auth:String,Key: String, Text: String): MutableLiveData<GetAddressList> {
        var apiResponse: MutableLiveData<GetAddressList> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getAddressListReqest(Key, Text)

        val call: Call<GetAddressList> = apiService.getAddressListReqest(GET_ADDRESS_LIST,auth, request)

        call.enqueue(object : Callback<GetAddressList> {

            override fun onResponse(
                call: Call<GetAddressList>,
                response: Response<GetAddressList>
            ) {
                Log.e(TAG, "onResponse response GetAddressList=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<GetAddressList>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}