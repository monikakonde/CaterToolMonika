package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.BrandDetailsRequest
import com.example.catertool.model.UpdateBrandDetails
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateBrandDeatilsRepository {
    val TAG = javaClass.simpleName
    var endpoints: ApiClient = ApiClient()

    fun doUpdateBrand( identifierNo:String, id:String,body: BrandDetailsRequest): MutableLiveData<UpdateBrandDetails> {
        var apiResponse: MutableLiveData<UpdateBrandDetails> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<UpdateBrandDetails> = apiService.putUpdateBrandRequest(identifierNo,id,body)

        call.enqueue(object: Callback<UpdateBrandDetails> {

            override fun onResponse(call: Call<UpdateBrandDetails>, response: Response<UpdateBrandDetails>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body())

                    if(response.body()!=null && response.body()!=null) {
                    }
                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<UpdateBrandDetails>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return apiResponse
    }
}