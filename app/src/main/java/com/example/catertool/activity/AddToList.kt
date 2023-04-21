package com.example.catertool.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityAddToListBinding
import com.example.catertool.model.AddToDoReqest
import com.example.catertool.viewmodel.AddTodoViewModel
import com.example.catertool.viewmodel.DeleteCommonCheckViewModel
import com.example.catertool.viewmodel.DeleteTodoViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class AddToList : AppCompatActivity() {
    private lateinit var binding: ActivityAddToListBinding
    private val viewModel: AddTodoViewModel by viewModels()
    var cal = Calendar.getInstance()
    private var UserId = ""
    private var token = ""
    private var brand_id = ""
    private var Selected_time = ""
    private var Selected_date = ""
    private var dayMin=""
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ToDoActvity::class.java)
        startActivity(intent)
        finish()
    }

    // listener which is triggered when the
    // time is picked from the time picker dialog
    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                val hour = if (hourOfDay < 10) "0$hourOfDay" else hourOfDay
                val min = if (minute < 10) "0$minute" else minute
                dayMin = "$hour:$min"
                Log.d("onTimeSet", "onTimeSet: 2  $dayMin")

                // logic to properly handle
                // the picked timings by user
                val formattedTime: String = when {
                    hourOfDay == 0 -> {
                        if (minute < 10) {
                            "${hourOfDay + 12}:0${minute} am"
                        } else {
                            "${hourOfDay + 12}:${minute} am"
                        }
                    }
                    hourOfDay > 12 -> {
                        if (minute < 10) {
                            "${hourOfDay - 12}:0${minute} pm"
                        } else {
                            "${hourOfDay - 12}:${minute} pm"
                        }
                    }
                    hourOfDay == 12 -> {
                        if (minute < 10) {
                            "${hourOfDay}:0${minute} pm"
                        } else {
                            "${hourOfDay}:${minute} pm"
                        }
                    }
                    else -> {
                        if (minute < 10) {
                            "${hourOfDay}:${minute} am"
                        } else {
                            "${hourOfDay}:${minute} am"
                        }
                    }
                }

                binding.tvTime.text = formattedTime
                Selected_time = formattedTime
                Log.d("onTimeSet", "onTimeSet: " + formattedTime)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        token = PreferenceHelper.preferences["jwt", ""]
        brand_id = PreferenceHelper.preferences["brand_id", ""]
        UserId = PreferenceHelper.preferences["UserId", ""]
        onClicks()
    }

    private fun onClicks() {

        val tvHeader = findViewById<AppCompatTextView>(R.id.tvHeader)
        tvHeader.text = "To Do"

        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        ivBackImg.setOnClickListener {
            finish()
        }

        binding.scTime.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                // pass the Context
                this,
                // listener to perform task
                // when time is picked
                timePickerDialogListener,
                // default hour when the time picker
                // dialog is opened
                12,
                // default minute when the time picker
                // dialog is opened
                10,
                // 24 hours time picker is
                // false (varies according to the region)
                false
            )

            // then after building the timepicker
            // dialog show the dialog to user
            timePicker.show()
            binding.tvTime.visibility = View.VISIBLE
        }

        binding.scDate.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker, year: Int, monthOfYear: Int,
                        dayOfMonth: Int
                    ) {
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, monthOfYear)
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        updateDateInView()
                    }
                }

                DatePickerDialog(
                    this@AddToList, dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        }

        binding.btnSave.setOnClickListener {
            val Title = binding.etTitle.text.toString().trim()
            val Notes = binding.etNotes.text.toString().trim()

            val sdf = SimpleDateFormat("'T'HH:mm:ssZ", Locale.UK)
            val iosTime = sdf.format(Date())
            AddToDo(Title, Notes, dayMin)

        }
    }

  //  2023-04-11T12:00:10.000Z
    private fun AddToDo(Title: String, Notes: String, dayMin: String) {
        val data: AddToDoReqest.Data = AddToDoReqest.Data(
            UserId,
            brand_id,
            Selected_date +"T"+dayMin+":00.000Z",
            Notes,
            "In Progress",
            Title
        )
        Log.d("AddToDo_test", "AddToDo: " + Selected_date +"T"+dayMin+":00.000Z")
        val addToDoReqest: AddToDoReqest = AddToDoReqest(data)

        viewModel.performAddToTO(token, addToDoReqest).observe(this) {
            if (it != null) {
                val intent = Intent(this, ToDoActvity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.tvDate.visibility = View.VISIBLE
        binding.tvDate!!.text = sdf.format(cal.getTime())
        Selected_date = sdf.format(cal.getTime()).toString()
    }


}