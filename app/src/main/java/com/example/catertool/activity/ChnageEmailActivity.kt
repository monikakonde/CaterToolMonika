package com.example.catertool.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.example.catertool.databinding.ActivityUserDetailsScreenBinding
import com.example.catertool.model.EmailUpdateReqest
import com.example.catertool.viewmodel.UpdateEmailViewModel

class ChnageEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChnageEmailBinding
    private var awesomeValidation1: AwesomeValidation? = null
    private val viewModel4: UpdateEmailViewModel by viewModels()
    var Email=""
    private var UserId = ""
    private var token="";
    private var brand_id="";

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CompanyDetailsActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChnageEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        token= PreferenceHelper.preferences["jwt", ""]
        brand_id= PreferenceHelper.preferences["brand_id", ""]
        UserId= PreferenceHelper.preferences["UserId", ""]
        awesomeValidation1 = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)

        Email = intent.getStringExtra("Email").toString()


        tvHeader?.setText("Change Email")
        binding.etCurrentEmailAddress?.setText(Email)


        ivBackImg.setOnClickListener {
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.etConfirmEmailAddress.doOnTextChanged { text, start, before, count ->
            if (binding.etNewEmailAddress.text.toString()
                    .equals(binding.etConfirmEmailAddress.text.toString())
            ) {
                binding.itConformEmailAddress.error = null
            } else {
                binding.itConformEmailAddress.error = "Passcode Doesn't Match"
            }
        }

        awesomeValidation1!!.addValidation(
            this@ChnageEmailActivity,
            R.id.it_NewEmailAddress,
            Patterns.EMAIL_ADDRESS,
            R.string.err_email
        )

        awesomeValidation1!!.addValidation(
            this@ChnageEmailActivity,
            R.id.it_ConformEmailAddress,
            R.id.it_NewEmailAddress,
            R.string.err_email_confirmation
        )

        binding.btnSubmitChanges.setOnClickListener {
            if (awesomeValidation1!!.validate()) {
                UpdateEmail()

            }
        }

    }

    private fun UpdateEmail() {
        val emailUpdateReqest: EmailUpdateReqest = EmailUpdateReqest(binding.etConfirmEmailAddress.text.toString().trim())
        viewModel4.performUpdateEmail(token,UserId,emailUpdateReqest).observe(this) {
            if (it != null) {
                val intent = Intent(this@ChnageEmailActivity, CompanyDetailsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
            }
        }
    }
}