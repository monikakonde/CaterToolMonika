package com.example.catertool.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.catertool.R
import com.example.catertool.databinding.ActivityPostalAddressBinding
import com.example.catertool.databinding.ActivityViewPostalAddressBinding

class ViewPostalAddress : AppCompatActivity() {
    private lateinit var binding: ActivityViewPostalAddressBinding

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CompanyDetailsActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_postal_address)
        binding = ActivityViewPostalAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitToolbar()

        val id = intent.getStringExtra("id")
        val CompanyName = intent.getStringExtra("CompanyName")
        val TradingName = intent.getStringExtra("TradingName")
        val Postcode = intent.getStringExtra("Postcode")
        val AddressLine1 = intent.getStringExtra("AddressLine1")
        val AddressLine2 = intent.getStringExtra("AddressLine2")
        val Town = intent.getStringExtra("Town")
        val County = intent.getStringExtra("County")
        val BusinessEmail = intent.getStringExtra("BusinessEmail")

        binding.tvPostalAddress.setText(AddressLine1)

        binding.tvEdit.setOnClickListener {
            val intent = Intent(this, UpdateAddress::class.java)
            intent.putExtra("id", id)
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

    }

    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        tvHeader?.setText("Postal Address")

        ivBackImg.setOnClickListener {
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}