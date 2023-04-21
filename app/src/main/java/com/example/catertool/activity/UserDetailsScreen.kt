package com.example.catertool.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityPostalAddressBinding
import com.example.catertool.databinding.ActivityUserDetailsScreenBinding
import com.example.catertool.viewmodel.DeleteCommonCheckViewModel
import com.example.catertool.viewmodel.DeleteUserBrandViewModel

class UserDetailsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsScreenBinding
    var Email=""
    var Mobile=""
    var UserName=""
    var ids=""
    private var UserId = ""
    private var token = "";
    private var brand_id = "";
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CompanyDetailsActivity::class.java)
        startActivity(intent)
        finish()
    }
    private val viewModel4: DeleteUserBrandViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token = PreferenceHelper.preferences["jwt", ""]
        brand_id = PreferenceHelper.preferences["brand_id", ""]
        UserId = PreferenceHelper.preferences["UserId", ""]

        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)

        uper?.setText("Delete User")
        uper?.setTextSize(15F)


        uper?.setOnClickListener {
            initDelateUser()
        }


        Email = intent.getStringExtra("Email").toString()
        Mobile = intent.getStringExtra("Mobile").toString()
        UserName = intent.getStringExtra("UserName").toString()
        ids = intent.getStringExtra("ids").toString()

        binding.RlMobileNuber.setOnClickListener {
            val intent = Intent(this, MobileNumChnageActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.rlChangeEmail.setOnClickListener {
            val intent = Intent(this, ChnageEmailActivity::class.java)
            intent.putExtra("Email", Email)
            startActivity(intent)
            finish()
        }

        tvHeader?.setText(UserName)
        binding.tvEmailName?.setText(Email)
        binding.tvMobileName?.setText(Mobile)

        ivBackImg.setOnClickListener {
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun initDelateUser() {
        viewModel4.performDelete_User_Brand(
            token,
            ids
        ).observe(this) {
            if (it != null) {
                val intent = Intent(this, CompanyDetailsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}