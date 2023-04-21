package com.example.catertool.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catertool.R
import com.example.catertool.databinding.ActivityPostalAddressBinding
import com.example.catertool.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this,LoginScreenActivity::class.java)
            intent.putExtra("Wellcome", "Wellcome")
            startActivity(intent)
            finish()
        }
    }
}