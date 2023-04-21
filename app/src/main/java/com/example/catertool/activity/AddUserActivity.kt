package com.example.catertool.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityAddUserBinding
import com.example.catertool.viewmodel.RegisterUserInsideViewModel
import es.dmoral.toasty.Toasty

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    private val sharedPrefFile = "Sharedpreference"
    private var Token = ""
    private var Comp_Details:Boolean=false
    var pd: ProgressDialog? = null
    private var awesomeValidation: AwesomeValidation? = null
    private val viewModel: RegisterUserInsideViewModel by viewModels()

    private var UserId = ""
    private var token="";
    private var brand_id="";
    private var userName="";
    private var is_company="0";
    private var is_Helth="0";
    private var is_OpeningClosing="0";
    private var is_TempratureRecord="0";
    private var is_SelesCost="0";
    private var is_Todo="0";

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CompanyDetailsActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        IntValidations()
        InitToolbar()

        token= PreferenceHelper.preferences["jwt", ""]
        brand_id= PreferenceHelper.preferences["brand_id", ""]
        UserId= PreferenceHelper.preferences["UserId", ""]
        userName= PreferenceHelper.preferences["username", ""]

    }

    private fun IntValidations() {
        binding.imgCheck.visibility = View.GONE
        binding.imgCheck2.visibility = View.GONE
        binding.imgCheck3.visibility = View.GONE
        binding.imgCheck4.visibility = View.GONE
        binding.imgCheck5.visibility = View.GONE
        binding.imgCheck6.visibility = View.GONE
        binding.tvCompanyDetails.setTextColor(Color.parseColor("#9B9B9B"));
        binding.tvHealthSafety.setTextColor(Color.parseColor("#9B9B9B"));
        binding.tvOpeningClosing.setTextColor(Color.parseColor("#9B9B9B"));
        binding.tvTemperature.setTextColor(Color.parseColor("#9B9B9B"));
        binding.tvSalesCost.setTextColor(Color.parseColor("#9B9B9B"));
        binding.tvToDoList.setTextColor(Color.parseColor("#9B9B9B"));

        binding.RlCompanyDetails.setOnClickListener {
            if (Comp_Details.equals(false)) {
                binding.imgCheck.visibility = View.VISIBLE
                binding.tvCompanyDetails.setTextColor(Color.parseColor("#FF000000"));
                binding.RlCompanyDetails.setBackgroundResource(R.drawable.bg_spinner_outline1);
                Comp_Details=true
                is_company="1"
            }else{
                binding.imgCheck.visibility = View.GONE
                binding.tvCompanyDetails.setTextColor(Color.parseColor("#9B9B9B"));
                binding.RlCompanyDetails.setBackgroundResource(R.drawable.bg_spinner_outline);
                Comp_Details=false
                is_company="0"
            }
        }

        binding.RlHelthandSefty.setOnClickListener {
            if (Comp_Details.equals(false)) {
                binding.imgCheck2.visibility = View.VISIBLE
                binding.tvHealthSafety.setTextColor(Color.parseColor("#FF000000"));
                binding.RlHelthandSefty.setBackgroundResource(R.drawable.bg_spinner_outline1);
                Comp_Details=true
                is_Helth="1"
            }else{
                binding.imgCheck2.visibility = View.GONE
                binding.tvHealthSafety.setTextColor(Color.parseColor("#9B9B9B"));
                binding.RlHelthandSefty.setBackgroundResource(R.drawable.bg_spinner_outline);
                Comp_Details=false
                is_Helth="0"
            }
        }

        binding.RlOpeningClosing.setOnClickListener {
            if (Comp_Details.equals(false)) {
                binding.imgCheck3.visibility = View.VISIBLE
                binding.tvOpeningClosing.setTextColor(Color.parseColor("#FF000000"));
                binding.RlOpeningClosing.setBackgroundResource(R.drawable.bg_spinner_outline1);
                Comp_Details=true
                is_OpeningClosing="1"
            }else{
                binding.imgCheck3.visibility = View.GONE
                binding.tvOpeningClosing.setTextColor(Color.parseColor("#9B9B9B"));
                binding.RlOpeningClosing.setBackgroundResource(R.drawable.bg_spinner_outline);
                Comp_Details=false
                is_OpeningClosing="0"
            }
        }

        binding.RlTempratureRecord.setOnClickListener {
            if (Comp_Details.equals(false)) {
                binding.imgCheck4.visibility = View.VISIBLE
                binding.tvTemperature.setTextColor(Color.parseColor("#FF000000"));
                binding.RlTempratureRecord.setBackgroundResource(R.drawable.bg_spinner_outline1);
                Comp_Details=true
                is_TempratureRecord="1"
            }else{
                binding.imgCheck4.visibility = View.GONE
                binding.tvTemperature.setTextColor(Color.parseColor("#9B9B9B"));
                binding.RlTempratureRecord.setBackgroundResource(R.drawable.bg_spinner_outline);
                Comp_Details=false
                is_TempratureRecord="0"
            }
        }

        binding.RlSelesCost.setOnClickListener {
            if (Comp_Details.equals(false)) {
                binding.imgCheck5.visibility = View.VISIBLE
                binding.tvSalesCost.setTextColor(Color.parseColor("#FF000000"));
                binding.RlSelesCost.setBackgroundResource(R.drawable.bg_spinner_outline1);
                Comp_Details=true
                is_SelesCost="1"
            }else{
                binding.imgCheck5.visibility = View.GONE
                binding.tvSalesCost.setTextColor(Color.parseColor("#9B9B9B"));
                binding.RlSelesCost.setBackgroundResource(R.drawable.bg_spinner_outline);
                Comp_Details=false
                is_SelesCost="0"
            }
        }

        binding.RlTodo.setOnClickListener {
            if (Comp_Details.equals(false)) {
                binding.imgCheck6.visibility = View.VISIBLE
                binding.tvSalesCost.setTextColor(Color.parseColor("#FF000000"));
                binding.RlTodo.setBackgroundResource(R.drawable.bg_spinner_outline1);
                Comp_Details=true
                is_Todo="1"
            }else{
                binding.imgCheck6.visibility = View.GONE
                binding.tvSalesCost.setTextColor(Color.parseColor("#9B9B9B"));
                binding.RlTodo.setBackgroundResource(R.drawable.bg_spinner_outline);
                Comp_Details=false
                is_Todo="0"
            }
        }

        awesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)

        awesomeValidation!!.addValidation(
            this@AddUserActivity,
            R.id.ip_Name,
            "[a-zA-Z\\s]+",
            R.string.invalid_firstname
        )
        awesomeValidation!!.addValidation(
            this@AddUserActivity,
            R.id.ip_LastName,
            "[a-zA-Z\\s]+",
            R.string.invalid_lastname
        )
        awesomeValidation!!.addValidation(
            this@AddUserActivity,
            R.id.ip_Mobile,
            "^[+]?[0-9]{12}$",
            R.string.err_tel
        )
//        awesomeValidation!!.addValidation(
//            this@AddUserActivity,
//            R.id.ip_Email,
//            Patterns.EMAIL_ADDRESS,
//            R.string.err_email
//        )
        awesomeValidation!!.addValidation(
            this@AddUserActivity,
            R.id.ip_Password,
            "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}",
            R.string.err_password
        )
        awesomeValidation!!.addValidation(
            this@AddUserActivity,
            R.id.ip_compformPass,
            R.id.ip_compformPass,
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
        tvHeader?.setText("Add User")

        ivBackImg.setOnClickListener {
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
        pd = ProgressDialog(this@AddUserActivity)
        pd!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd!!.setMessage(resources.getString(R.string.loading))

        binding.btnAdd.setOnClickListener {
            if (awesomeValidation!!.validate()) {
                // Here, we are sure that form is successfully validated. So, do your stuffs now...
                //  Toast.makeText(UserDetailsJActivity.this, "Form Validated Successfully", Toast.LENGTH_LONG).show();
                val FirstName = binding.etFirstName.text.toString().trim()
                val LastName = binding.etLastName.text.toString().trim()
                val Mobile = binding.etMobile.text.toString().trim()
                val Password = binding.etPassword.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val ConfirmPassword = binding.etConfirmPassword.text.toString().trim()
                if (!binding.etPassword.text.toString()
                        .equals(binding.etConfirmPassword.text.toString())
                ) {
                    Toast.makeText(
                        this@AddUserActivity,
                        "Confirm password is not correct",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    RegisterUser(FirstName, LastName, Mobile, Password,email)
                }
            }
        }
    }

    private fun RegisterUser(
        FirstName: String,
        LastName: String,
        Mobile: String,
        Password: String,
        email: String) {
        viewModel.performUserInsideRegister(Mobile, Mobile+"@catertool.com",Password,"true","false",FirstName,LastName,Mobile,is_company,is_Helth,is_TempratureRecord,is_OpeningClosing,is_TempratureRecord,is_Todo,brand_id).observe(this) {
            if (it != null && !TextUtils.isEmpty(it.jwt)) {
                    Log.d("RegisterfwefweUsera", "RegisterUser: Objaver recvied data"+is_Todo+"   "+is_TempratureRecord+"   "+is_OpeningClosing+"   "+is_SelesCost+"   "+is_Todo)
                    it.user?.id
//                    val id: String? = it.user?.id
//                    val username: String? = it.user?.username
//                    val email: String? = it.user?.email
//                    val provider: String? = it.user?.provider
//                    val confirmed: String? = it.user?.confirmed
//                    val blocked: String? = it.user?.blocked
//                    val createdAt: String? = it.user?.createdAt
//                    val updatedAt: String? = it.user?.updatedAt
//                    val firstName: String? = it.user?.firstName
//                    val lastName: String? = it.user?.lastName
//                    val telephone: String? = it.user?.telephone
//                    val mobile: String? = it.user?.mobile
//                    val otpConfirmed: String? = it.user?.otpConfirmed


                    val intent = Intent(this, CompanyDetailsActivity::class.java)
                    intent.putExtra("AddUser", "1")
                    startActivity(intent)
                    finish()
            } else {

                //  Toast.makeText(applicationContext, "Failed Login!"+ it?.error?.message, Toast.LENGTH_SHORT).show()
                Toasty.error(applicationContext, "Email or Username are already taken", Toast.LENGTH_SHORT, true).show();
            }
        }
    }
}