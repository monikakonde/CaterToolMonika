package com.example.catertool.activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import com.example.catertool.R
import com.example.catertool.databinding.ActivityAddSalesRecordFormBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class AddSalesRecordFormActivity : BaseActivity() {

private lateinit var binding: ActivityAddSalesRecordFormBinding

    override fun onBackPressed() {
        super.onBackPressed()
    }
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddSalesRecordFormBinding.inflate(layoutInflater)
    setContentView(binding.root)
   var Ids = intent.getIntExtra("Id",0).toString()
   val unitName = intent.getStringExtra("unitName").toString()
    binding.etUnitNmae.setText(unitName)
    onClicks()
}

fun onClicks() {

    val tvHeader = findViewById<AppCompatTextView>(R.id.tvHeader)
    tvHeader.text = "Add Sales Record"

    val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
    ivBackImg.setOnClickListener {
        finish()
    }

   var unitName = intent.getStringExtra("unitName").toString()

    binding.etEventName.doOnTextChanged { text, start, before, count ->
        if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
            binding.ettillEventName.error = "Please Enter Right Details"
        } else {

            binding.ettillEventName.error = null
        }
    }
    binding.etDate.doOnTextChanged { text, start, before, count ->
//        if (!Pattern.matches(".*[a-zA-Z]+.*", text)) {
//            binding.ettillDate.error = "Please Enter Right Details"
//        } else {
//
//            binding.ettillDate.error = null
//        }
    }
    binding.etCash.doOnTextChanged { text, start, before, count ->
        if (!Pattern.matches("^[0-9]", text)) {
            binding.ettillCash.error = "Please Enter Right Details"
        } else {

            binding.ettillCash.error = null
        }
    }

    binding.etCard.doOnTextChanged { text, start, before, count ->
        if (!Pattern.matches("^[0-9]", text)) {
            binding.ettillCard.error = "Please Enter Right Details"
        } else {

            binding.ettillCard.error = null
        }
    }

    binding.etOther.doOnTextChanged { text, start, before, count ->
        if (!Pattern.matches("^[0-9]", text)) {
            binding.ettillOther.error = "Please Enter Right Details"
        } else {

            binding.ettillOther.error = null
        }
    }

}
}