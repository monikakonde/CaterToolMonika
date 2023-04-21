package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.AddChecksReqest
import com.example.catertool.model.UpdateChecks
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateAllCheckDeatilsRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doUpdateallchecks( identifierNo:String,body: AddChecksReqest): MutableLiveData<UpdateChecks> {
        var apiResponse: MutableLiveData<UpdateChecks> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<UpdateChecks> = apiService.putUpdateAllChecksRequest(identifierNo,body)

        call.enqueue(object: Callback<UpdateChecks> {

            override fun onResponse(call: Call<UpdateChecks>, response: Response<UpdateChecks>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<UpdateChecks>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}