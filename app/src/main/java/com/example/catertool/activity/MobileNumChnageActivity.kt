package com.example.catertool.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityChnageEmailBinding
import com.example.catertool.databinding.ActivityMobileNumChnageBinding
import com.example.catertool.model.EmailUpdateReqest
import com.example.catertool.model.MobileNumberUpdateRequest
import com.example.catertool.viewmodel.UpdateEmailViewModel
import com.example.catertool.viewmodel.UpdateMobileViewModel
import java.util.regex.Pattern

class MobileNumChnageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMobileNumChnageBinding
    private var awesomeValidation1: AwesomeValidation? = null
    var Email=""
    private var UserId = ""
    private var token="";
    private var brand_id="";
    private var userName="";
    private val viewModel4: UpdateMobileViewModel by viewModels()
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CompanyDetailsActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMobileNumChnageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token= PreferenceHelper.preferences["jwt", ""]
        brand_id= PreferenceHelper.preferences["brand_id", ""]
        UserId= PreferenceHelper.preferences["UserId", ""]
        userName= PreferenceHelper.preferences["username", ""]

        awesomeValidation1 = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)

      //  Email = intent.getStringExtra("Email").toString()


        tvHeader?.setText("Change Mobile Number")
        binding.etUserName?.setText(userName)


        ivBackImg.setOnClickListener {
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.etNewMobileNumber.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches("^[0-9]{12}$", text)) {
                binding.itNewMobileNumber.error = "Please Enter Right Mobile No"
            } else {
                binding.itNewMobileNumber.error = null
            }
        }

        awesomeValidation1!!.addValidation(
            this@MobileNumChnageActivity,
            R.id.it_NewMobileNumber,
            "^[+]?[0-9]{12}$",
            R.string.err_tel
        )

        binding.btnSubmitChanges.setOnClickListener {
            if (awesomeValidation1!!.validate()) {
                UpdateMobile()
            }
        }

    }

    private fun UpdateMobile() {
        val emailUpdateReqest: MobileNumberUpdateRequest = MobileNumberUpdateRequest(binding.etNewMobileNumber.text.toString().trim(),userName)
        viewModel4.performUpdateMobile(token,UserId,emailUpdateReqest).observe(this) {
            if (it != null) {
                val intent = Intent(this@MobileNumChnageActivity, CompanyDetailsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
            }
        }
    }

}