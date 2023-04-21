package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityOtpVerificationBinding
import com.example.catertool.model.ResendOtp
import com.example.catertool.viewmodel.LoginViewModel
import com.example.catertool.viewmodel.ResendOtpModel
import com.example.catertool.viewmodel.VerifyOtpModel
import es.dmoral.toasty.Toasty


class OtpVerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpVerificationBinding
    var pd: ProgressDialog? = null
    var id = ""
    private val viewModel: VerifyOtpModel by viewModels()
    private val viewModel1: ResendOtpModel by viewModels()
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, UserRegister::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTitlError.visibility = View.GONE
        pd = ProgressDialog(this@OtpVerificationActivity)
        pd?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd?.setMessage(resources.getString(R.string.loading))

        val intent = intent
        id = intent.getStringExtra("id").toString()
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val provider = intent.getStringExtra("provider")
        val confirmed = intent.getStringExtra("confirmed")
        val blocked = intent.getStringExtra("blocked")
        val createdAt = intent.getStringExtra("createdAt")
        val updatedAt = intent.getStringExtra("updatedAt")
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val telephone = intent.getStringExtra("telephone")
        val mobile = intent.getStringExtra("mobile")
        var mobile_num= PreferenceHelper.preferences["mobile", ""]
        binding.tvSecureApp.setText("Please enter 6 digit code sent to you\nat " + mobile_num)
        val otpConfirmed = intent.getStringExtra("otpConfirmed")


        binding.LlReSendOtp.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, OtpVerificationActivity::class.java)
            startActivity(intent)
            finish()
            ResendOtp(mobile.toString())
        })

        binding.LlChangeMobileNo.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UserRegister::class.java)
            startActivity(intent)
            finish()
        })

        binding.btnVerify.setOnClickListener {
            var otps =binding.PinView.text.toString()
            OtpVerification(mobile_num, otps)
        }
    }

    private fun OtpVerification(mobile: String, otp: String) {
        pd?.show()
        viewModel.performLogin(mobile, otp).observe(this) {
            Log.d("Otpchakingss", "Otpchakingss: " + mobile + " " + otp)
            Log.e("LoginActivity", "login response received: $it")
            if (it != null && !TextUtils.isEmpty(it.jwt)) {
                it.user?.id
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


                //  Toast.makeText(applicationContext, "Success Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AddBusinessDetailsActivity::class.java)
                intent.putExtra("id", UserId)
                intent.putExtra("username", username)
                intent.putExtra("email", email)
                intent.putExtra("provider", provider)
                intent.putExtra("confirmed", confirmed)
                intent.putExtra("blocked", blocked)
                intent.putExtra("createdAt", createdAt)
                intent.putExtra("updatedAt", updatedAt)
                intent.putExtra("firstName", firstName)
                intent.putExtra("lastName", lastName)
                intent.putExtra("telephone", telephone)
                intent.putExtra("mobile", mobile)
                intent.putExtra("otpConfirmed", otpConfirmed)
                startActivity(intent)
                finish()
            } else {
                pd?.dismiss()
                binding.tvTitlError.visibility = View.VISIBLE
                //   Toast.makeText(applicationContext, "Otp not match!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun ResendOtp(mobile: String) {
        pd?.show()
        viewModel1.performLogin(mobile).observe(this) {
            Log.d("Otpchakingss", "Otpchakingss: " + mobile + " ")
            Log.e("LoginActivity", "login response received: $it")
            if (it != null) {
                it.sent
                pd?.dismiss()
                //    Toast.makeText(applicationContext, "Otp Send", Toast.LENGTH_SHORT).show()
                Toasty.success(applicationContext, "Otp Send", Toast.LENGTH_SHORT, true).show();


            } else {
                pd?.dismiss()
                Toasty.error(applicationContext, "Otp Sending Failed!", Toast.LENGTH_SHORT, true)
                    .show();
                //  Toast.makeText(applicationContext, "Otp Sending Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}