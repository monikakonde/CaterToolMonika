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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.set
import com.example.catertool.R
import com.example.catertool.databinding.ActivityLoginScreenBinding
import com.example.catertool.viewmodel.LoginViewModel
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

class LoginScreenActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    // private val viewModel: LoginViewModel by viewModel()
    private val viewModel by viewModel<LoginViewModel>()

    private val sharedPrefFile = "Sharedpreference"
    private val sharedPrefFile1 = "SaveLoginSharedpreference"

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val intent = intent
        val Wellcome = intent.getStringExtra("Wellcome").toString()
        if (Wellcome.equals("Wellcome")) {
            binding.btnNewUser.visibility = View.GONE
        }

        binding.etEmail.doOnTextChanged { text, start, before, count ->
            if (text?.isEmpty()!!) {
                binding.tilEmail.error = null
            } else {
                if (!Pattern.matches("^[0-9]{12}$", text)) {
                    binding.tilEmail.error = "Please Enter Right Mobile No"
                } else {
                    Log.d("onCreatewq", "onCreate002: " + text)
                    binding.tilEmail.error = null
                }
            }

        }

        binding.etPassword.doOnTextChanged { text, start, before, count ->
            if (text?.isEmpty()!!) {
                binding.tilPassword.error = null
            } else {
                if (binding.etPassword.text.toString().isEmpty()) {
                    binding.tilPassword.error = "Password Should not be blank"
                } else {
                    binding.tilPassword.error = null
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.text.toString().isEmpty() && binding.etPassword.text.toString()
                    .isEmpty()
            ) {
                binding.tilEmail.error = "Mobile Should not be blank"
                binding.tilPassword.error = "Password Should not be blank"
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.tilPassword.error = "Password Should not be blank"
            } else {
                val Email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                emailLogin(Email, password)
            }
        }

        binding.btnNewUser.setOnClickListener {
            val intent = Intent(this, UserRegister::class.java)
            //   val intent = Intent(this, AddBusinessDetailsActivity::class.java)
            //   val intent = Intent(this, OtpVerificationActivity::class.java)
            //    val intent = Intent(this,PostalAddressActivity::class.java)

            startActivity(intent)
            finish()
        }

        binding.tvForgetpass.setOnClickListener {
            val intent = Intent(this, ForgetPasswordaActivity::class.java)
            startActivity(intent)
            finish()
        }

        checkSharedPreference()
    }

    private fun emailLogin(email: String, password: String) {
         showProgressDialog()
        Log.d("emailLogin", "emailLogin: " + email + " " + password)
        viewModel.performLogin(email, password).observe(this) {
            Log.e("LoginActivity", "login response received: $it")
            if (it != null && !TextUtils.isEmpty(it.jwt)) {
                it.user?.id
                val jwt = "Bearer ${it?.jwt}"
                PreferenceHelper.preferences["jwt"] = jwt
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
                val brand_id = it.brand_details?.id
                val companyName = it.brand_details?.companyName
                val noOfUsers = it.brand_details?.noOfUsers

                PreferenceHelper.preferences["brand_id"] = brand_id
                PreferenceHelper.preferences["companyName"] = companyName
                PreferenceHelper.preferences["noOfUsers"] = noOfUsers
                PreferenceHelper.preferences["jwt"] = jwt
                PreferenceHelper.preferences["UserId"] = UserId
                PreferenceHelper.preferences["username"] = username
                PreferenceHelper.preferences["email"] = email
                PreferenceHelper.preferences["provider"] = provider
                PreferenceHelper.preferences["confirmed"] = confirmed
                PreferenceHelper.preferences["blocked"] = blocked
                PreferenceHelper.preferences["createdAt"] = createdAt
                PreferenceHelper.preferences["updatedAt"] = updatedAt
                PreferenceHelper.preferences["firstName"] = firstName
                PreferenceHelper.preferences["lastName"] = lastName
                PreferenceHelper.preferences["username"] = username
                PreferenceHelper.preferences["telephone"] = telephone
                PreferenceHelper.preferences["mobile"] = mobile
                PreferenceHelper.preferences["otpConfirmed"] = otpConfirmed

                // val name:String = inputName.text.toString()
                val sharedPreferences: SharedPreferences =
                    this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("Token", jwt)
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
                if (binding.saveLoginCheckBox.isChecked) {
                    editor1.putString(getString(R.string.checkBox), "True")
                    editor1.commit()
                    editor1.apply()
                    val strEmail = binding.etEmail.text.toString().trim()
                    editor1.putString(getString(R.string.name), strEmail)
                    editor1.commit()
                    editor1.apply()
                    val strPassword = binding.etPassword.text.toString().trim()
                    editor1.putString(getString(R.string.password), strPassword)
                    editor1.commit()
                    editor1.apply()
                } else {
                    editor1.putString(getString(R.string.checkBox), "False")
                    editor1.commit()
                    editor1.apply()
                    editor1.putString(getString(R.string.name), "")
                    editor1.commit()
                    editor1.apply()
                    editor1.putString(getString(R.string.password), "")
                    editor1.commit()
                    editor1.apply()
                }

                Toasty.success(applicationContext, "Login Successful!", Toast.LENGTH_SHORT, true)
                    .show()
                hideProgressDialog()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                hideProgressDialog()
                Toasty.error(applicationContext, "Invalid Credentials!", Toast.LENGTH_SHORT, true)
                    .show();
            }
        }
    }

    private fun checkSharedPreference() {
        val sharedPreferences1: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile1, Context.MODE_PRIVATE)
        val strCheckBox =
            sharedPreferences1.getString(getString(R.string.checkBox), "False").toString()
        val strName = sharedPreferences1.getString(getString(R.string.name), "").toString()
        val strPassword = sharedPreferences1.getString(getString(R.string.password), "").toString()
        Log.d("checkSharedPreference", "checkSharedPreference: " + strName)
        binding.etEmail.setText(strName)
        binding.etPassword.setText(strPassword)
        binding.saveLoginCheckBox.isChecked = strCheckBox == "True"
    }
}

