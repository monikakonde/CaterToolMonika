package com.example.catertool.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivitySelaseEventDetailsBinding
import java.text.DecimalFormat

class SelaseEventDetailsActivity : AppCompatActivity() {
    val df = DecimalFormat("#.##")
    private lateinit var binding: ActivitySelaseEventDetailsBinding
    private var UserId = ""
    private var token = ""
    private var brand_id = ""

    @SuppressLint("SetTextI18n", "StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelaseEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token = PreferenceHelper.preferences["jwt", ""]
        brand_id = PreferenceHelper.preferences["brand_id", ""]
        UserId = PreferenceHelper.preferences["UserId", ""]

        InitToolbar()
        val eventDate = intent.getStringExtra("EventDate")
        val eventName = intent.getStringExtra("EventName")
        val cash = intent.getDoubleExtra("Cash", 0.0)
        val card = intent.getDoubleExtra("Card", 0.0)
        val other = intent.getDoubleExtra("Other", 0.0)
        val unitName = intent.getStringExtra("UnitName")

        binding.tvTitle.text = eventName

        binding.tvEventName.text = eventName
        binding.tvDate.text = eventDate
        binding.tvCash.text = df.format(cash)
        binding.tvCard.text = df.format(card)
        binding.tvOther.text = df.format(other)
        binding.tvVehicle.text = unitName.toString()

        val result = cash + card + other
        binding.tvTotal.text = String.format(getString(R.string.totalCartValue, df.format(result)))
    }

    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        tvHeader?.setText("Sales")

        ivBackImg.setOnClickListener {
            val intent = Intent(this, SalesCostTrackerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}