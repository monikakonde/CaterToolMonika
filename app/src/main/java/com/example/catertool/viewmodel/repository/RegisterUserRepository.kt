package com.example.catertool.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catertool.model.RegisterUser
import com.example.catertool.network.RetrofitRequest
import com.example.catertool.network.ApiClient
import com.tobibur.swipequotes.model.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUserRepository(private var endpoints: ApiClient) {
    val TAG = javaClass.simpleName
    //  var endpoints: ApiClient = ApiClient()

    fun performUserRegister(
        username: String,
        email: String,
        password: String,
        confirmed: String,
        blocked: String,
        firstName: String,
        lastName: String,
        mobile: String
    ): MutableLiveData<RegisterUser> {
        var apiResponse: MutableLiveData<RegisterUser> = MutableLiveData()

        val apiService: ApiInterface = endpoints.getClient()!!.create(ApiInterface::class.java)

        val request: Map<String, String> = RetrofitRequest.userRegister(
            username, email, password, confirmed, blocked, firstName, lastName, mobile
        )

        val call: Call<RegisterUser> = apiService.userRegisterRequest(request)

        call.enqueue(object : Callback<RegisterUser> {

            override fun onResponse(call: Call<RegisterUser>, response: Response<RegisterUser>) {
                Log.e(TAG, "onResponse response=" + response.toString())

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body())

                    if (response.body() != null && response.body() != null) {
                    }
                } else {

                }
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<RegisterUser>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
                Log.d("onResponse", "onFailure: ${t.message}")
                t.message
            }

        })

        return apiResponse
    }
}