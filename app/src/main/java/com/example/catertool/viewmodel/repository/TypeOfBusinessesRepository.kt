package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.TypeOfBusinesses
import com.google.gson.Gson
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeOfBusinessesRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun getTypeOfBusinesses(): MutableLiveData<TypeOfBusinesses> {
        var apiResponse: MutableLiveData<TypeOfBusinesses> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<TypeOfBusinesses> = apiService.typeOfBusinessesRequest()

        call.enqueue(object: Callback<TypeOfBusinesses> {

            override fun onResponse(call: Call<TypeOfBusinesses>, response: Response<TypeOfBusinesses>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }else{
                    Log.d("onResponse", "onResponse01: ${Gson().toJson(response)}")

                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<TypeOfBusinesses>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
                Log.d("onResponse", "onFailure: ${t.message}")
                t.message
            }

        })

        return apiResponse
    }
}