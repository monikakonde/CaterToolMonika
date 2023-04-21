package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityUpdateAddressBinding
import com.example.catertool.model.BrandDetailsRequest
import com.example.catertool.model.UpdatePostalAddressReqest
import com.example.catertool.viewmodel.*
import java.util.regex.Pattern

class UpdateAddress : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateAddressBinding
    var pd: ProgressDialog? = null
    var BusinessName = ""
    var Ids = ""
    var Date_company = ""
    var TradingName = ""
    var ssBusinessType = ""
    var ssTypeOfBusiness = ""
    var BusinessEmail = ""

    var TypeOfBusinessesId = 0
    var BusinessTypeId = 0
    var username = ""
    var email = ""
    var provider = ""
    var confirmed = ""
    var blocked = ""
    var createdAt = ""
    var updatedAt = ""
    var firstName = ""
    var lastName = ""
    var telephone = ""
    var mobile = ""
    var otpConfirmed = ""
    var EnterPostcode=""
    var Address1=""
    var  Address2=""
    var Towne=""
    var Postcode=""
    lateinit var recuArray: List<String>
    lateinit var CountryIdArray1: List<String>
   var CountryId=0
    var token=""
    var brand_id=""
    var UserId = ""
    lateinit var Main_Address_Id: List<String>
    private var awesomeValidation: AwesomeValidation? = null
    private val viewModel: RegisterBusinessViewModel by viewModels()
    private val viewModel1: CountryListViewModel by viewModels()
    private val viewModel2: GetAllAddressListViewModel by viewModels()
    private val viewModel3: UpdatePostalAddressViewModel by viewModels()
    private val viewModel4: GetAddressViewModel by viewModels()

    override fun onBackPressed() {
        super.onBackPressed()
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_address)
        binding = ActivityUpdateAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        IntValidations();

        token= PreferenceHelper.preferences["jwt", ""]
        brand_id= PreferenceHelper.preferences["brand_id", ""]
        UserId= PreferenceHelper.preferences["UserId", ""]

        val id = intent.getStringExtra("id")
        val CompanyName = intent.getStringExtra("CompanyName")
        val TradingName = intent.getStringExtra("TradingName")
        val Postcode = intent.getStringExtra("Postcode")
        val AddressLine1 = intent.getStringExtra("AddressLine1")
        val AddressLine2 = intent.getStringExtra("AddressLine2")
        val Town = intent.getStringExtra("Town")
        val County = intent.getStringExtra("County")
        val BusinessEmail = intent.getStringExtra("BusinessEmail")

        Log.d("check_datahhh", "BusinessName: 1" + BusinessName)
        Log.d("check_datahhh", "TradingName: 2" + TradingName)
        Log.d("check_datahhh", "BusinessEmail: 3" + BusinessEmail)
        Log.d("check_datahhh", "TypeOfBusinessesId: 4" + TypeOfBusinessesId)
        Log.d("check_datahhh", "BusinessTypeId: 5" + BusinessTypeId)
        Log.d("check_datahhh", "UserId: 4" + UserId)

        binding.etAddress1.setText(AddressLine1)
        binding.etAddress2.setText(AddressLine2)
        binding.etTowne.setText(Town)
        binding.etcounty.setText(County)
        binding.etPostcode.setText(Postcode)


        binding.etAddress1.doOnTextChanged { text, start, before, count ->
            if (!Pattern.matches(".*[a-zA-Z0-9]+.*", text)) {
                binding.tillAddress1.error = "Please fill valid data "
            } else {

                binding.tillAddress1.error = null
            }
        }

//        binding.etAddress2.doOnTextChanged { text, start, before, count ->
//            if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
//                binding.tillAddress2.error = "Please fill valid data "
//            } else {
//
//                binding.tillAddress2.error = null
//            }
//        }

//        binding.etTowne.doOnTextChanged { text, start, before, count ->
//            if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
//                binding.tillTowne.error = "Please fill valid data "
//            } else {
//
//                binding.tillTowne.error = null
//            }
//        }

        getCuntryList()
        pd = ProgressDialog(this@UpdateAddress)
        pd?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd?.setMessage(resources.getString(R.string.loading))

        val intent = intent
        Ids = intent.getStringExtra("id").toString()
        Date_company = intent.getStringExtra("Data").toString()

        InitToolbar()

        username = intent.getStringExtra("username").toString()
        email = intent.getStringExtra("email").toString()
        provider = intent.getStringExtra("provider").toString()
        confirmed = intent.getStringExtra("confirmed").toString()
        blocked = intent.getStringExtra("blocked").toString()
        createdAt = intent.getStringExtra("createdAt").toString()
        updatedAt = intent.getStringExtra("updatedAt").toString()
        firstName = intent.getStringExtra("firstName").toString()
        lastName = intent.getStringExtra("lastName").toString()
        telephone = intent.getStringExtra("telephone").toString()
        mobile = intent.getStringExtra("mobile").toString()
        otpConfirmed = intent.getStringExtra("otpConfirmed").toString()



        binding.ssCuntryList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                //     Handle the selected item
                ssBusinessType = parent.getItemAtPosition(position) as String
                CountryId = CountryIdArray1[position].toInt()
                //    Toast.makeText(this@AddBusinessDetailsActivity, "Selected: $ssBusinessType", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

            val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
            ivBackImg.setOnClickListener {
                val intent = Intent(this, CompanyDetailsActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.btnSubmit.setOnClickListener {
                if (awesomeValidation!!.validate()) {
                    val Address1 = binding.etAddress1.text.toString().trim()
                    val Address2 = binding.etAddress2.text.toString().trim()
                    val County = binding.etcounty.text.toString().trim()
                    val Postcode = binding.etPostcode.text.toString().trim()
                    val Towne = binding.etTowne.text.toString().trim()
                    UpdatePostalAddressDetails(Address1, Address2,County, Towne, Postcode)
                }
            }

        }

    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        tvHeader?.setText("Postal Address")

    }

    private fun UpdatePostalAddressDetails(
        Address1: String,
        Address2: String,
        County: String,
        Towne: String,
        Postcode: String
    ) {
        val data: UpdatePostalAddressReqest.Data = UpdatePostalAddressReqest.Data(Address1, Address2,CountryId,County,Postcode,Towne)
        val updatePostalAddressReqest: UpdatePostalAddressReqest = UpdatePostalAddressReqest(data)
        viewModel3.performUpdatePostalAddress(token,brand_id,updatePostalAddressReqest).observe(this) {
            if (it != null) {
                val intent = Intent(this@UpdateAddress, DetailUpdatedActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                //    Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getCuntryList() {
        viewModel1.performCountryList().observe(this) {
            if (it != null) {
                 recuArray = it.data?.map { it.attributes?.Name } as ArrayList<String>
                CountryIdArray1 = it.data?.map { it.id.toString() } as List<String>
                var geographic = ArrayAdapter(this, R.layout.item_spinner, recuArray)
                geographic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.ssCuntryList.setAdapter(geographic)
            } else {
                Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun IntValidations() {
        awesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)

        awesomeValidation!!.addValidation(
            this@UpdateAddress,
            R.id.tillAddress1,
            "[a-zA-Z0-9\\s]+",
            R.string.Pleasefillvaliddata
        )

        awesomeValidation!!.addValidation(
            this@UpdateAddress,
            R.id.tillTowne,
            "[a-zA-Z0-9\\s]+",
            R.string.Pleasefillvaliddata
        )

        awesomeValidation!!.addValidation(
            this@UpdateAddress,
            R.id.tillPostcade,
            "[a-zA-Z0-9\\s]+",
            R.string.Pleasefillvaliddata
        )
    }

}
