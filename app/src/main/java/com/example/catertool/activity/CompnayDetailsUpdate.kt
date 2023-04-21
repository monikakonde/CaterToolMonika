package com.example.catertool.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityCompnayDetailsUpdateBinding
import com.example.catertool.model.BrandDetailsRequest
import com.example.catertool.viewmodel.BusinessesTypeViewModel
import com.example.catertool.viewmodel.TypeOfBusinessesViewModel
import com.example.catertool.viewmodel.UpdateBrandDetailsViewModel

class CompnayDetailsUpdate : BaseActivity() {
    private lateinit var binding: ActivityCompnayDetailsUpdateBinding

    private val viewModel: TypeOfBusinessesViewModel by viewModels()
    private val viewModel1: BusinessesTypeViewModel by viewModels()
    private val viewModel3: UpdateBrandDetailsViewModel by viewModels()

    var ssTypeOfBusiness=""
    var ssBusinessType=""
    private var Token = ""
    private val sharedPrefFile = "Sharedpreference"

    private var UserId = ""
    private var token="";
    private var brand_id="";
    lateinit var recuArray1: List<String>
    lateinit var businessTypeArray: List<String>
    var TypeOfBusines_ID = 0
    var businessType_ID = 0
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CompanyDetailsActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompnayDetailsUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token= PreferenceHelper.preferences["jwt", ""]
        brand_id= PreferenceHelper.preferences["brand_id", ""]
        UserId= PreferenceHelper.preferences["UserId", ""]

        val Company_Name = intent.getStringExtra("Company_Name")
        val Trading_name = intent.getStringExtra("Trading_name")

        binding.etBusinessName.setText(Company_Name)
        binding.etTradingName.setText(Trading_name)
        InitToolbar()
        initTypeOfBusinesses()
        initBusinessType()


        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        ivBackImg.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSave.setOnClickListener {
//            UpdateEmail()
            if (binding.etBusinessName.text.toString().isEmpty()) {
                binding.etBusinessName.error = "BusinessName Should not be blank"
            }else if (binding.etTradingName.text.toString().isEmpty()) {
                binding.etTradingName.error = "TradingName Should not be blank"
            } else {
                UpdateBrandDetails()
            }
        }

        binding.ssBusinessType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //     Handle the selected item
                ssBusinessType = parent.getItemAtPosition(position) as String
                businessType_ID =businessTypeArray[position].toInt()
                val messageToShow = "Selected: $ssBusinessType , $businessType_ID"
             //   Toast.makeText(this@CompnayDetailsUpdate, messageToShow, Toast.LENGTH_SHORT).show()

            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }


        binding.ssTypeOfBusiness.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //     Handle the selected item
                ssTypeOfBusiness = parent.getItemAtPosition(position) as String
                TypeOfBusines_ID = recuArray1[position].toInt()
               // Toast.makeText(this@CompnayDetailsUpdate, "Selected: $ssTypeOfBusiness", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

    }


    private fun initTypeOfBusinesses() {
        viewModel.performTypeOfBusinesses().observe(this) {
            if (it != null) {
                val recuArray = it.data?.map { it.attributes?.Name } as ArrayList<String>
                recuArray1 = it.data?.map { it.id.toString() } as List<String>
                recuArray.add(0, "Please Select Type Of Business ")
                (recuArray1 as ArrayList<String>).add(0,"0")
                val geographic = ArrayAdapter(this, R.layout.item_spinner, recuArray)
                geographic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.ssTypeOfBusiness.setAdapter(geographic)

            } else {
            }
        }
    }
    private fun InitToolbar() {

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        Token = sharedPreferences.getString("Token","").toString()
        UserId = sharedPreferences.getString("UserId","").toString()

        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        LlScreens?.setVisibility(View.GONE);
        tvHeader?.setText("Company Details")

        ivBackImg.setOnClickListener {
            finish()
        }
    }

    private fun initBusinessType() {
        viewModel1.performBusinessTypes().observe(this) {
            if (it != null) {

                val recuArray = it.data?.map { it.attributes?.Name } as ArrayList<String>
                businessTypeArray = it.data?.map { it.id.toString() } as ArrayList<String>
                recuArray.add(0, "Please Select Business Type")
                (businessTypeArray as ArrayList<String>).add(0,"0")
                val geographic = ArrayAdapter(this, R.layout.item_spinner, recuArray)
                geographic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.ssBusinessType.setAdapter(geographic)

            } else {
            }
        }
    }

    private fun UpdateBrandDetails() {
        val data: BrandDetailsRequest.Data = BrandDetailsRequest.Data(businessType_ID, binding.etBusinessName.text.toString(),binding.etTradingName.text.toString(),TypeOfBusines_ID)
        val brandDetailsRequest: BrandDetailsRequest = BrandDetailsRequest(data)
        viewModel3.performUpdateBrand(Token, brand_id,brandDetailsRequest).observe(this) {
            if (it != null) {
                val intent = Intent(this@CompnayDetailsUpdate, DetailUpdatedActivity::class.java)
                startActivity(intent)
                finish()
            } else {
            //    Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}