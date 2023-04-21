package com.example.catertool.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.catertool.R

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "Sharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val UserId = sharedPreferences.getString("UserId","")
        val username = sharedPreferences.getString("username","")
        Log.d("UserId", "onCreate: "+username+"    "+UserId)
        Handler(Looper.getMainLooper()).postDelayed({
            if (!username.equals("")){
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginScreenActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 2000)
    }
}