package com.example.catertool.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.adapter.GetApiUserAdpater
import com.example.catertool.databinding.ActivityCompanyDetailsBinding
import com.example.catertool.viewmodel.*
import com.google.android.material.tabs.TabLayout

class CompanyDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompanyDetailsBinding
    private val viewModel1: UseMeViewModel by viewModels()
    private val viewModel2: GetBrandDetailsViewModel by viewModels()
    private val viewModel3: GetApiUserModel by viewModels()
    private val sharedPrefFile = "Sharedpreference"
    private var ed_Email = ""
    private var UserId = ""
    private var token = "";
    private var brand_id = "";
    private var AddUser = "";
    private var CompanyName = "";
    private var TradingName = "";
    private var Postcode = "";
    private var AddressLine1 = "";
    private var AddressLine2 = "";
    private var Town = "";
    private var County = "";
    private var Countrys = "";
    private var BusinessEmail = "";
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token = PreferenceHelper.preferences["jwt", ""]
        brand_id = PreferenceHelper.preferences["brand_id", ""]
        UserId = PreferenceHelper.preferences["UserId", ""]

        val intent = intent
        AddUser = intent.getStringExtra("AddUser").toString()
        if (AddUser.equals("1")){
            binding.tabLayout.getTabAt(1)?.select()
            binding.scroll.visibility = View.GONE
            binding.LlAddUers.visibility = View.GONE
            binding.RlAllUserList.visibility = View.VISIBLE
            initGetApiUserList()
        }else{
            binding.scroll.visibility = View.VISIBLE
            binding.LlAddUers.visibility = View.GONE
            binding.RlAllUserList.visibility = View.GONE
        }

        InitToolbar()
        initBusinessType()
        initGetBrandDetails()
    }

    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        tvHeader?.setText("Company Details")

        binding.recycleView.layoutManager = LinearLayoutManager(this)

        ivBackImg.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

//        binding.rlEditPostalAddress.setOnClickListener {
//            val intent = Intent(this, PostalAddressActivity::class.java)
//            intent.putExtra("id", UserId)
//            intent.putExtra("Data", "pass")
//            startActivity(intent)
//            finish()
//        }

        binding.rlEditPostalAddress.setOnClickListener {
            val intent = Intent(this, ViewPostalAddress::class.java)
            intent.putExtra("id", UserId)
            intent.putExtra("CompanyName", CompanyName)
            intent.putExtra("TradingName", TradingName)
            intent.putExtra("Postcode", Postcode)
            intent.putExtra("AddressLine1", AddressLine1)
            intent.putExtra("AddressLine2", AddressLine2)
            intent.putExtra("Town", Town)
            intent.putExtra("County", County)
            intent.putExtra("BusinessEmail", BusinessEmail)
            startActivity(intent)
            finish()
        }

        binding.rlChangeMobile.setOnClickListener {
            val intent = Intent(this, MobileNumChnageActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.RlCompanyDetails.setOnClickListener {
            val intent = Intent(this, CompnayDetailsUpdate::class.java)
            intent.putExtra("Company_Name", CompanyName)
            intent.putExtra("Trading_name", TradingName)
            startActivity(intent)
            finish()
        }

        binding.rlChangeEmail.setOnClickListener {
            val intent = Intent(this, ChnageEmailActivity::class.java)
            intent.putExtra("Email", ed_Email)
            startActivity(intent)
            finish()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.scroll.visibility = View.GONE
                val position = tab!!.position

                if (position == 1) {
                    initGetApiUserList()
                    binding.scroll.visibility = View.GONE
                } else {
                    binding.scroll.visibility = View.VISIBLE
                    binding.LlAddUers.visibility = View.GONE
                    binding.RlAllUserList.visibility = View.GONE
                }
                Log.d("TAfwefweG", "onTabSelected: " + position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val position = tab!!.position

                if (position == 1) {
                    binding.scroll.visibility = View.GONE
                    binding.LlAddUers.visibility = View.VISIBLE
                } else {
                    binding.scroll.visibility = View.VISIBLE
                    binding.LlAddUers.visibility = View.GONE
                }
            }

        })

        binding.ivAddUser.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.imgAddUser.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initBusinessType() {
        viewModel1.performUseMe(token)
            .observe(this) {
                if (it != null) {
                    Log.d("initType", "initBusinessType01: ${it.lastName + "  " + it.firstName}")
                } else {
                    //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun initGetBrandDetails() {
        viewModel2.performGetBrandDetails(
            token,
            UserId,
            "*"
        ).observe(this) {
            if (it != null) {
                Log.d("initBrandDetails", "initBusinessTypegregre br: ${it.data}")
                CompanyName=it.data.last().attributes.companyName
                TradingName=it.data.last().attributes.tradingName
                Postcode=it.data.last().attributes.postcode
                AddressLine1=it.data.last().attributes.addressLine1
                AddressLine2=it.data.last().attributes.addressLine2
                Town=it.data.last().attributes.town
                County=it.data.last().attributes.county
                Countrys=it.data.last().attributes.country.data.attributes.Name
                BusinessEmail=it.data.last().attributes.businessEmail
                binding.tvCompanyName.setText(it.data.last().attributes.companyName)
                binding.tvTradingName.setText(it.data.last().attributes.tradingName)
                binding.tvPasscode.setText(it.data.last().attributes.postcode)
                binding.tvEmailName.setText(it.data.last().attributes.users.data.last().attributes.email)
                binding.tvMobileName.setText(it.data.last().attributes.users.data.last().attributes.mobile)
                ed_Email = it.data.last().attributes.users.data.last().attributes.email
                binding.tvTypeOfBusiness.setText(it.data.last().attributes.typeOfBusiness.data.attributes.Name)
                binding.tvBusinessType.setText(it.data.last().attributes.businessType.data.attributes.Name)
            } else {
             //   binding.LlAddUers.visibility = View.VISIBLE
               // Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initGetApiUserList() {
        Log.d("hbvidhvbwe", "uvfwueyvfuw: " + "   " + token)
        Log.d("hbvidhvbwe", "wefwefwef: " + "    " + UserId)
        viewModel3.performGetApiUserList(
            token,
            "*",
            brand_id
        ).observe(this) {
            if (it != null) {
                if (it.size > 0) {

                    binding.scroll.visibility = View.GONE
                    binding.LlAddUers.visibility = View.GONE
                    binding.RlAllUserList.visibility = View.VISIBLE

                    binding.recycleView.setAdapter(
                        GetApiUserAdpater(it)
                    )
                } else {
                    binding.scroll.visibility = View.GONE
                    binding.LlAddUers.visibility = View.VISIBLE
                    binding.RlAllUserList.visibility = View.GONE
                }
            } else {
                binding.RlAllUserList.visibility = View.VISIBLE
              //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}