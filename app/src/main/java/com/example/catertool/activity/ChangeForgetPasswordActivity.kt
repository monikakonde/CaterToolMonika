package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.example.catertool.R
import com.example.catertool.databinding.ActivityChangeForgetPasswordBinding
import com.example.catertool.databinding.ActivityForgetPasswordaBinding
import com.example.catertool.databinding.ActivityLoginScreenBinding
import com.example.catertool.viewmodel.LoginViewModel
import com.example.catertool.viewmodel.ResetPasswordViewModel

class ChangeForgetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeForgetPasswordBinding
    private val viewModel: ResetPasswordViewModel by viewModels()
    private val sharedPrefFile = "Sharedpreference"
    private val sharedPrefFile1 = "SaveLoginSharedpreference"
    var pd: ProgressDialog? = null
    var MobileNo = ""
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ForgetPasswordaActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        MobileNo = intent.getStringExtra("MobileNo").toString()

        pd = ProgressDialog(this@ChangeForgetPasswordActivity)
        pd?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd?.setMessage(resources.getString(R.string.loading))

        binding.imgBack.setOnClickListener {
            val intent = Intent(this, ForgetPasswordaActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.etnewpass.doOnTextChanged { text, start, before, count ->
            if (text!!.length<6){
                binding.tillnewpassword.error="Please Enter Min 6 key"
            }else{
                binding.tillnewpassword.error=null
            }
        }

        binding.etConformpass.doOnTextChanged { text, start, before, count ->
            if (binding.etConformpass.text.toString().equals(binding.etnewpass.text.toString())){
                binding.tillConformpassword.error=null
            }else{
                binding.tillConformpassword.error="Passcode Doesn't Match"
            }
        }

        binding.btnSendOtp.setOnClickListener {
            if (binding.etOTP.text.toString().isEmpty()) {
                binding.etOTP.error = "Otp Should not be blank"
            } else if (binding.etnewpass.text.toString()
                    .isEmpty() && binding.etConformpass.text.toString().isEmpty()
            ) {
                binding.etnewpass.error = "Password Should not be blank"
            } else {
                val otp = binding.etOTP.text.toString().trim()
                val password = binding.etnewpass.text.toString().trim()
                val conform_password = binding.etConformpass.text.toString().trim()
                passwordReset(otp, password, conform_password)
            }
        }

    }

    private fun passwordReset(otp: String, password: String, conform_password: String) {
        pd?.show()
        Log.d("emailLogin", "emailLogin: " +MobileNo+"   "+ otp + " " + password+"   "+conform_password)
        viewModel.performResetPassword(MobileNo, otp, password, conform_password).observe(this) {
            Log.e("LoginActivity", "login response received: $it")
            if (it != null && !TextUtils.isEmpty(it.jwt)) {
                it.user?.id
                val jwt = "Bearer ${it?.jwt}"
                val UserId = it.user?.id
                val username = it.user?.username
                val email = it.user?.email
                val provider = it.user?.provider
                val confirmed = it.user?.confirmed
                val blocked = it.user?.blocked
                val createdAt = it.user?.createdAt
                val updatedAt = it.user?.updatedAt
                val firstName = it.user?.firstName
                val lastName = it.user?.lastName
                val telephone = it.user?.telephone
                val mobile = it.user?.mobile
                val otpConfirmed = it.user?.otpConfirmed

                // val name:String = inputName.text.toString()
                val sharedPreferences: SharedPreferences =
                    this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("UserId", UserId)
                editor.putString("username", username)
                editor.putString("email", email)
                editor.putString("provider", provider)
                editor.putString("confirmed", confirmed)
                editor.putString("blocked", blocked)
                editor.putString("createdAt", createdAt)
                editor.putString("updatedAt", updatedAt)
                editor.putString("firstName", firstName)
                editor.putString("lastName", lastName)
                editor.putString("username", username)
                editor.putString("telephone", telephone)
                editor.putString("mobile", mobile)
                editor.putString("otpConfirmed", otpConfirmed)
                editor.apply()
                editor.commit()

                val sharedPreferences1: SharedPreferences =
                    this.getSharedPreferences(sharedPrefFile1, Context.MODE_PRIVATE)
                val editor1: SharedPreferences.Editor = sharedPreferences1.edit()
                   Toast.makeText(applicationContext, "Password change Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
            } else {
                pd?.dismiss()
                //   Toast.makeText(applicationContext, "Failed Login!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

