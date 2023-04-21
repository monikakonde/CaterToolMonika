package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.catertool.databinding.ActivityPostalAddressBinding
import com.example.catertool.viewmodel.*
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern

class PostalAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostalAddressBinding
    var pd: ProgressDialog? = null
    var BusinessName = ""
    var Ids = ""
    var Date_company = ""
    var TradingName = ""
    var ssBusinessType = ""
    var ssTypeOfBusiness = ""
    var BusinessEmail = ""
    var UserId = ""
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
    lateinit var Main_Address_Id: List<String>
    private var awesomeValidation: AwesomeValidation? = null
    private val viewModel: RegisterBusinessViewModel by viewModels()
    private val viewModel1: CountryListViewModel by viewModels()
    private val viewModel2: GetAllAddressListViewModel by viewModels()
    private val viewModel3: GetAllMainAddressListViewModel by viewModels()
    private val viewModel4: GetAddressViewModel by viewModels()
    override fun onBackPressed() {
        super.onBackPressed()
        if (Date_company.equals("pass")) {
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, AddBusinessDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postal_address)
        binding = ActivityPostalAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        IntValidations();

        binding.LlMainAddress.visibility=View.GONE
        binding.LlAddressList.visibility=View.GONE
        binding.btnEnterAddressManual.setOnClickListener {
           binding.LlManualAddress.visibility = View.VISIBLE
        }

        binding.btnFindByPostcode.setOnClickListener {
            var postcode=binding.etEnterPostcode.text.toString().trim()
            if (binding.etEnterPostcode.text.toString().isEmpty()) {
                binding.tillEnterPostcode.error = "Postcode Should not be blank"
            } else {
                binding.tillEnterPostcode.error = null
                initBusinessType1(postcode)
            }

        }
        binding.etAddress1.doOnTextChanged { text, start, before, count ->
            if (text?.isEmpty() == true) {
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
        pd = ProgressDialog(this@PostalAddressActivity)
        pd?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd?.setMessage(resources.getString(R.string.loading))

        val intent = intent
        Ids = intent.getStringExtra("id").toString()
        Date_company = intent.getStringExtra("Data").toString()

        InitToolbar()
        BusinessName = intent.getStringExtra("BusinessName").toString()
        TradingName = intent.getStringExtra("TradingName").toString()
        ssBusinessType = intent.getStringExtra("ssBusinessType").toString()
        ssTypeOfBusiness = intent.getStringExtra("ssTypeOfBusiness").toString()
        BusinessEmail = intent.getStringExtra("BusinessEmail").toString()
        UserId = intent.getStringExtra("UserId").toString()
        TypeOfBusinessesId = intent.getIntExtra("TypeOfBusinessesId", 0)
        BusinessTypeId = intent.getIntExtra("BusinessTypeId", 0)

        Log.d("check_data", "BusinessName: 1" + BusinessName)
        Log.d("check_data", "TradingName: 2" + TradingName)
        Log.d("check_data", "BusinessEmail: 3" + BusinessEmail)
        Log.d("check_data", "TypeOfBusinessesId: 4" + TypeOfBusinessesId)
        Log.d("check_data", "BusinessTypeId: 5" + BusinessTypeId)
        Log.d("check_data", "UserId: 4" + UserId)

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

        binding.ssAddressList.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //     Handle the selected item
                    ssBusinessType = parent.getItemAtPosition(position) as String
                  var Main_AddressId = Main_Address_Id[position]
                    initBusinessType3(Main_AddressId)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Do nothing
                }
            }

        binding.ssCuntryList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                //     Handle the selected item
                ssBusinessType = parent.getItemAtPosition(position) as String
                BusinessTypeId = (id + 1).toInt()
                //    Toast.makeText(this@AddBusinessDetailsActivity, "Selected: $ssBusinessType", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }


        if (Date_company.equals("pass")) {
            val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
            ivBackImg.setOnClickListener {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.btnSubmit.setOnClickListener {
                if (awesomeValidation!!.validate()) {
                     Address1 = binding.etAddress1.text.toString().trim()
                     Address2 = binding.etAddress2.text.toString().trim()
                     Towne = binding.etTowne.text.toString().trim()
                     Postcode = binding.etPostcode.text.toString().trim()
                    businessDetail1(
                        Address1,
                        Address2,
                        Towne,
                        Postcode
                    )
                }
            }
        } else {
            val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
            ivBackImg.setOnClickListener {
                val intent = Intent(this, AddBusinessDetailsActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.btnSubmit.setOnClickListener {
                if (awesomeValidation!!.validate()) {

                    val EnterPostcode = binding.etPostcode.text.toString().trim()
                    val Address1 = binding.etAddress1.text.toString().trim()
                    val Address2 = binding.etAddress2.text.toString().trim()
                    val Towne = binding.etTowne.text.toString().trim()
                    val Postcode = binding.etPostcode.text.toString().trim()
                    Log.d("InitToolbar", "InitToolbar: 2" + Date_company)
                    if (binding.cbTermsCondition.isChecked) {
                        businessDetail_Login_Side(
                            Address1,
                            Address2,
                            Towne,
                            Postcode
                        )
                    } else {
                        Toasty.error(
                            applicationContext,
                            "Pls Agree Terms and Conditions!",
                            Toast.LENGTH_SHORT,
                            true
                        ).show();
                    }
                }
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
        uper?.setText("3")
        bello?.setText("/3")

        if (Date_company.equals("pass")) {
            Log.d("InitToolbar", "InitToolbar: 1" + Date_company)
            LlScreens?.setVisibility(View.GONE);
            binding.Llconditions.setVisibility(View.GONE)
        } else {
            Log.d("InitToolbar", "InitToolbar: 2" + Date_company)
            LlScreens?.setVisibility(View.VISIBLE);
        }

        ivBackImg.setOnClickListener {
            finish()
        }
    }

    private fun businessDetail_Login_Side(address1: String,address2: String, towne: String, postcode: String) {
        Log.d("businessDetail1", "businessDetail1: 1")
        pd?.show()
        viewModel.performBusinessRegister(
            UserId,
            BusinessName,
            TradingName,
            postcode,
            address1,
            "1",
            BusinessTypeId.toString(),
            BusinessEmail,
            TypeOfBusinessesId.toString()
        ).observe(this) {
            Log.d("check_datag", "UserId:" + UserId)
            Log.d("check_datag", "BusinessName: " + BusinessName)
            Log.d("check_datag", "TradingName:" + TradingName)
            Log.d("check_datag", "address1" + address1)
            Log.d("check_datag", "BusinessTypeId" + BusinessTypeId)
            Log.d("check_datag", "BusinessEmail" + BusinessEmail)
            Log.d("check_datag", "TypeOfBusinessesId" + TypeOfBusinessesId)
            if (it != null) {
                it.registration_result?.message
                Toast.makeText(
                    applicationContext,
                    "${it.registration_result?.message}",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                pd?.dismiss()
                Toast.makeText(
                    applicationContext,
                    "Failed Login!" + it?.error?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun businessDetail1(
        address1: String,
        address2: String,
        towne: String,
        postcode: String
    ) {
        pd?.show()
        viewModel.performBusinessRegister(
            UserId,
            BusinessName,
            TradingName,
            postcode,
            address1,
            "1",
            BusinessTypeId.toString(),
            BusinessEmail,
            TypeOfBusinessesId.toString()
        ).observe(this) {
            if (it != null) {
                it.registration_result?.message
                val intent = Intent(this, DetailUpdatedActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                pd?.dismiss()
                Toast.makeText(
                    applicationContext,
                    "Failed Login!" + it?.error?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getCuntryList() {
        viewModel1.performCountryList().observe(this) {
            if (it != null) {
                val recuArray = it.data?.map { it.attributes?.Name } as ArrayList<String>
                val geographic = ArrayAdapter(this, R.layout.item_spinner, recuArray)
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
            this@PostalAddressActivity,
            R.id.tillAddress1,
            "[a-zA-Z0-9\\s]+",
            R.string.Pleasefillvaliddata
        )
//        awesomeValidation!!.addValidation(
//            this@PostalAddressActivity,
//            R.id.tillAddress2,
//            "[a-zA-Z\\s]+",
//            R.string.Pleasefillvaliddata
//        )

        awesomeValidation!!.addValidation(
            this@PostalAddressActivity,
            R.id.tillTowne,
            "[a-zA-Z0-9\\s]+",
            R.string.Pleasefillvaliddata
        )

        awesomeValidation!!.addValidation(
            this@PostalAddressActivity,
            R.id.tillPostcade,
            "[a-zA-Z0-9\\s]+",
            R.string.Pleasefillvaliddata
        )
    }

    private fun initBusinessType1(postcode: String) {
        Log.d("CheckPostcodeid", "onCreate: 2" +postcode)
        viewModel2.performGetAddressList("","EE93-TB43-DP19-TY89",postcode).observe(this) {
            if (it != null) {
                var PostCodeId = it.Items.last().Id
                Log.d("CheckPostcodeid", "onCreate: 3" +PostCodeId)
                initBusinessType2(PostCodeId)
            } else {
                Toast.makeText(applicationContext, "Failed! 1", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initBusinessType2(PostCodeId: String) {
        viewModel3.performMainAddressList("","EE93-TB43-DP19-TY89",PostCodeId).observe(this) {
            if (it != null) {
                val recuArray = it.Items?.map { it.Text} as ArrayList<String>
                val geographic = ArrayAdapter(this, R.layout.item_spinner, recuArray)
                if (recuArray.size>0){
                    binding.LlMainAddress.visibility=View.VISIBLE
                    binding.LlAddressList.visibility=View.VISIBLE
                }else{
                    binding.LlMainAddress.visibility=View.VISIBLE
                    binding.LlAddressList.visibility=View.VISIBLE
                }
                Main_Address_Id = it.Items?.map { it.Id } as List<String>
                geographic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.ssAddressList.setAdapter(geographic)
            } else {
                Toast.makeText(applicationContext, "Failed! 2", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initBusinessType3(Main_AddressId: String) {
        viewModel4.performGetAddress("","EE93-TB43-DP19-TY89",Main_AddressId).observe(this) {
            if (it != null) {
                var recu = it.Items?.last()?.Label
                Towne = it.Items?.last()?.City.toString()
                Address1 = it.Items?.last()?.Line1.toString()
                Address2 = it.Items?.last()?.Line2.toString()
                EnterPostcode = it.Items?.last()?.PostalCode!!
                binding.etAddress1.setText(recu)
                binding.etAddress2.setText(Address2)
                binding.etPostcode.setText(EnterPostcode)
                binding.etTowne.setText(Towne)
                binding.tvMainAddress.setText(recu)
            } else {
                Toast.makeText(applicationContext, "Failed! 3", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
