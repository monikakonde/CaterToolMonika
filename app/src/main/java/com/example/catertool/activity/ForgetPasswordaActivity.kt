package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.example.catertool.R
import com.example.catertool.databinding.ActivityForgetPasswordaBinding
import com.example.catertool.viewmodel.ForgotPasswordMobileModel
import com.example.catertool.viewmodel.LoginViewModel
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern


class ForgetPasswordaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordaBinding
    private val viewModel: ForgotPasswordMobileModel by viewModels()

    var pd: ProgressDialog? = null
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgetPasswordaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etMobileNumber.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches("^[0-9]{12}$", text)) {
                binding.tilMobileNumber.error = "Please Enter Right Mobile No"
            } else {
                binding.tilMobileNumber.error =null
            }
        }

        binding.btnSendOtp.setOnClickListener {
            if (binding.etMobileNumber.text.toString().isEmpty()) {
                binding.tilMobileNumber.error = "Mobile Should not be blank"
            } else {
                val MobileNum = binding.etMobileNumber.text.toString().trim()
                binding.tilMobileNumber.error = null
                ForgotPasswordMobile(MobileNum)
            }
        }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.imgBack.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }



    }

    private fun ForgotPasswordMobile(mobile: String) {
        pd?.show()
        viewModel.performForgotPasswordMobile(mobile).observe(this) {
            Log.d("Otpchakingss", "Otpchakingss: " + mobile + " ")
            if (it != null) {
                pd?.dismiss()
                Toasty.success(applicationContext, "OTP should be send successfully!", Toast.LENGTH_SHORT, true).show();
                val intent = Intent(this, ChangeForgetPasswordActivity::class.java)
                intent.putExtra("MobileNo", binding.etMobileNumber.text.toString())
                startActivity(intent)
                finish()

            } else {
                pd?.dismiss()
                Toasty.error(applicationContext, "OTP sending faild!", Toast.LENGTH_SHORT, true)
                    .show();
            }
        }
    }
}