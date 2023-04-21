package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.set
import com.example.catertool.R
import com.example.catertool.databinding.ActivityUserRegisterBinding
import com.example.catertool.viewmodel.LoginViewModel
import com.example.catertool.viewmodel.RegisterUserViewModel
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

class UserRegister : AppCompatActivity() {
    private lateinit var binding: ActivityUserRegisterBinding
    var pd: ProgressDialog? = null
    private var awesomeValidation: AwesomeValidation? = null

    //private val viewModel: RegisterUserViewModel by viewModels()
    private val viewModel by viewModel<RegisterUserViewModel>()
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitToolbar()
        IntValidations()
        pd = ProgressDialog(this@UserRegister)
        pd!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd!!.setMessage(resources.getString(R.string.loading))

        binding.etFirstName.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
                binding.tilFirstName.error = "Please Enter Right Name"
            } else {

                binding.tilFirstName.error = null
            }
        }
        binding.etLastName.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
                binding.tilLastName.error = "Please Enter Right Name"
            } else {
                binding.tilLastName.error = null
            }
        }

        binding.etMobile.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches("^[0-9]{12}$", text)) {
                binding.tilMobile.error = "Please Enter Right Mobile No"
            } else {
                binding.tilMobile.error = null
            }
        }

        binding.etPassword.doOnTextChanged { text, start, before, count ->
            if (text!!.length < 6) {
                binding.tilPassword.error = "Please Enter Min 6 key"
            } else {
                binding.tilPassword.error = null
            }
        }

        binding.etConfirmPassword.doOnTextChanged { text, start, before, count ->
            if (binding.etPassword.text.toString()
                    .equals(binding.etConfirmPassword.text.toString())
            ) {
                binding.tilConfirmPassword.error = null
            } else {
                binding.tilConfirmPassword.error = "Passcode Doesn't Match"
            }
        }

        binding.btnNext.setOnClickListener {
            if (awesomeValidation!!.validate()) {
                // Here, we are sure that form is successfully validated. So, do your stuffs now...
                // Toast.makeText(UserDetailsJActivity.this, "Form Validated Successfully", Toast.LENGTH_LONG).show();
                val FirstName = binding.etFirstName.text.toString().trim()
                val LastName = binding.etLastName.text.toString().trim()
                val Mobile = binding.etMobile.text.toString().trim()
                val Password = binding.etPassword.text.toString().trim()
                val ConfirmPassword = binding.etConfirmPassword.text.toString().trim()

                if (Password!!.length >= 6) {
                    binding.tilPassword.error = null
                    if (!binding.etPassword.text.toString()
                            .equals(binding.etConfirmPassword.text.toString())
                    ) {
                        binding.tilConfirmPassword.error = "Confirm password is not correct"
                    } else {
                        RegisterUser(FirstName, LastName, Mobile, Password)
                        binding.tilConfirmPassword.error = null

                    }
                } else {
                    binding.tilPassword.error = "Please Enter Min 6 key"
                }
            }
        }
    }

    private fun IntValidations() {
        awesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)

        awesomeValidation!!.addValidation(
            this@UserRegister,
            R.id.tilFirstName,
            "[a-zA-Z\\s]+",
            R.string.invalid_firstname
        )
        awesomeValidation!!.addValidation(
            this@UserRegister,
            R.id.tilLastName,
            "[a-zA-Z\\s]+",
            R.string.invalid_lastname
        )
        awesomeValidation!!.addValidation(
            this@UserRegister,
            R.id.tilMobile,
            "^[+]?[0-9]{12}$",
            R.string.err_tel
        )
        awesomeValidation!!.addValidation(
            this@UserRegister,
            R.id.tilEmail,
            Patterns.EMAIL_ADDRESS,
            R.string.err_email
        )
//        awesomeValidation!!.addValidation(
//            this@UserRegister,
//            R.id.tilPassword,
//             "{6}$",
//            R.string.err_password
//        )
        awesomeValidation!!.addValidation(
            this@UserRegister,
            R.id.tilConfirmPassword,
            R.id.tilPassword,
            R.string.err_password_confirmation
        )
    }

    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        LlScreens?.setVisibility(View.VISIBLE);
        tvHeader?.setText("User Details")
        uper?.setText("1")
        bello?.setText("/3")

        ivBackImg.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun RegisterUser(
        FirstName: String,
        LastName: String,
        Mobile: String,
        Password: String
    ) {
        pd?.show()
        viewModel.performUserRegister(
            Mobile,
            Mobile + "@catertool.com",
            Password,
            "true",
            "false",
            FirstName,
            LastName,
            Mobile
        ).observe(this) {
            if (it != null) {
                it.user?.id
                val id: String? = it.user?.id
                val username: String? = it.user?.username
                val email: String? = it.user?.email
                val provider: String? = it.user?.provider
                val confirmed: String? = it.user?.confirmed
                val blocked: String? = it.user?.blocked
                val createdAt: String? = it.user?.createdAt
                val updatedAt: String? = it.user?.updatedAt
                val firstName: String? = it.user?.firstName
                val lastName: String? = it.user?.lastName
                val telephone: String? = it.user?.telephone
                val mobile: String? = it.user?.mobile
                val otpConfirmed: String? = it.user?.otpConfirmed

                PreferenceHelper.preferences["UserId"] = id
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

                //  Toast.makeText(applicationContext, "Success Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, OtpVerificationActivity::class.java)
                intent.putExtra("id", id)
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
                //  Toast.makeText(applicationContext, "Failed Login!"+ it?.error?.message, Toast.LENGTH_SHORT).show()
                Toasty.error(
                    applicationContext,
                    "Email or Username are already taken",
                    Toast.LENGTH_SHORT,
                    true
                ).show();
            }
        }
    }
}


