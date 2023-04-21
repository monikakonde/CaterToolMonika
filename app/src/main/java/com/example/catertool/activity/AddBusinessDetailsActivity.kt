package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.catertool.R
import com.example.catertool.databinding.ActivityAddBusinessDetailsBinding
import com.example.catertool.viewmodel.BusinessesTypeViewModel
import com.example.catertool.viewmodel.GetAllAddressListViewModel
import com.example.catertool.viewmodel.TypeOfBusinessesViewModel
import java.util.regex.Pattern

class AddBusinessDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBusinessDetailsBinding
    private var awesomeValidation1: AwesomeValidation? = null
    private val viewModel: TypeOfBusinessesViewModel by viewModels()
    private val viewModel2: BusinessesTypeViewModel by viewModels()

    var pd: ProgressDialog? = null
    var ssTypeOfBusiness = ""
    var ssBusinessType = ""
    var TypeOfBusines_ID = 0
    var BusinessType_Id = 0
    lateinit var recuArray: List<String>
    lateinit var recuArray1: List<String>
    lateinit var Array_TypeOfBusines_ID: List<String>

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, OtpVerificationActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBusinessDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        awesomeValidation1 = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
        InitToolbar()
        initTypeOfBusinesses()
        initBusinessTypes()

        binding.etBusinessName.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
                binding.tinBusinessName.error = "Please fill valid data "
            } else {

                binding.tinBusinessName.error = null
            }
        }
        binding.etBusinessemail.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
                binding.tinEmailName.error = "Please fill valid data "
            } else {

                binding.tinEmailName.error = null
            }
        }

        binding.etTradingName.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
                binding.tinTradingName.error = "Please fill valid data "
            } else {

                binding.tinTradingName.error = null
            }
        }

        val intent = intent
        val id = intent.getStringExtra("id")
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
        val otpConfirmed = intent.getStringExtra("otpConfirmed")

        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        ivBackImg.setOnClickListener {
            val intent = Intent(this, OtpVerificationActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnNext.setOnClickListener {
            if (awesomeValidation1!!.validate()) {
                val intent =
                    Intent(this@AddBusinessDetailsActivity, PostalAddressActivity::class.java)
                intent.putExtra("BusinessName", binding.etBusinessName.text.toString().trim())
                intent.putExtra("TradingName", binding.etTradingName.text.toString().trim())
                intent.putExtra("BusinessEmail", binding.etBusinessemail.text.toString().trim())
                intent.putExtra("ssBusinessType", ssBusinessType)
                intent.putExtra("ssTypeOfBusiness", ssTypeOfBusiness)
                intent.putExtra("TypeOfBusinessesId", TypeOfBusines_ID)
                intent.putExtra("BusinessTypeId", BusinessType_Id)

                intent.putExtra("UserId", id)
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
                startActivity(intent)
                //          }
            }
        }

        binding.ssBusinessType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //     Handle the selected item
                    ssBusinessType = parent.getItemAtPosition(position) as String
                    BusinessType_Id = recuArray1[position].toInt()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Do nothing
                }
            }


        binding.ssTypeOfBusiness.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    //     Handle the selected item
                    ssTypeOfBusiness = parent.getItemAtPosition(position) as String
                    TypeOfBusines_ID = Array_TypeOfBusines_ID[position].toInt()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
    }
    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)


        pd = ProgressDialog(this@AddBusinessDetailsActivity)
        pd!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd!!.setMessage(resources.getString(R.string.loading))

        awesomeValidation1!!.addValidation(
            this@AddBusinessDetailsActivity,
            R.id.tin_Business_Name,
            "[a-zA-Z\\s]+",
            R.string.Pleasefillvaliddata
        )
        awesomeValidation1!!.addValidation(
            this@AddBusinessDetailsActivity,
            R.id.tin_Trading_Name,
            "[a-zA-Z\\s]+",
            R.string.invalidTradingName
        )
        awesomeValidation1!!.addValidation(
            this@AddBusinessDetailsActivity,
            R.id.tin_Email_Name,
            Patterns.EMAIL_ADDRESS,
            R.string.invalidEmailName
        )


        LlScreens?.setVisibility(View.VISIBLE);
        tvHeader?.setText("Business Details")
        uper?.setText("2")
        bello?.setText("/3")

        ivBackImg.setOnClickListener {
            finish()
        }
    }

    private fun initTypeOfBusinesses() {
        viewModel.performTypeOfBusinesses().observe(this) {
            if (it != null) {
                recuArray = it.data?.map { it.attributes?.Name } as List<String>
                Array_TypeOfBusines_ID = it.data?.map { it.id.toString() } as List<String>

                val geographic = ArrayAdapter(this, R.layout.item_spinner, recuArray)
                geographic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.ssTypeOfBusiness.setAdapter(geographic)
            } else {
            }
        }
    }

    private fun initBusinessTypes() {
        viewModel2.performBusinessTypes().observe(this) {
            if (it != null) {
                recuArray = it.data?.map { it.attributes?.Name } as List<String>
                recuArray1 = it.data?.map { it.id.toString() } as List<String>
                val geographic = ArrayAdapter(this, R.layout.item_spinner, recuArray)
                geographic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.ssBusinessType.setAdapter(geographic)
            } else {
            }
        }
    }

}