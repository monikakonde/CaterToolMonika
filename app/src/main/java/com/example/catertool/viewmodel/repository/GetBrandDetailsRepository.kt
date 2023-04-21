package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.GetBrandDetails
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetBrandDetailsRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doGetBrandDetails(auth:String,filter: String, populate: String): MutableLiveData<GetBrandDetails> {
        var apiResponse: MutableLiveData<GetBrandDetails> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.getBrandDetailReqest(filter, populate)

        val call: Call<GetBrandDetails> = apiService.getBrandDetailsReqest(
            auth,
            request
        )

        call.enqueue(object : Callback<GetBrandDetails> {

            override fun onResponse(
                call: Call<GetBrandDetails>,
                response: Response<GetBrandDetails>
            ) {
                Log.e(TAG, "onResponse response GetBrandDetails=$response")

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<GetBrandDetails>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return apiResponse
    }
}