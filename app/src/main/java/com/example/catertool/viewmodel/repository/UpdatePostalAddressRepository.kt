package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.UpdatePostalAddressReqest
import com.example.catertool.model.UpdatePostalAddress
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePostalAddressRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doUpdatePostalAddress( identifierNo:String, id:String,body: UpdatePostalAddressReqest): MutableLiveData<UpdatePostalAddress> {
        var apiResponse: MutableLiveData<UpdatePostalAddress> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<UpdatePostalAddress> = apiService.putUpdatePostalAddressRequest(identifierNo,id,body)

        call.enqueue(object: Callback<UpdatePostalAddress> {

            override fun onResponse(call: Call<UpdatePostalAddress>, response: Response<UpdatePostalAddress>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<UpdatePostalAddress>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}