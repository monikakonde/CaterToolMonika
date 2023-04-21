package com.example.catertool.activity

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catertool.R
import java.text.SimpleDateFormat
import java.util.*

open class BaseActivity : AppCompatActivity() {
    var pd: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pd = ProgressDialog(this)
        pd?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd?.setMessage(resources.getString(R.string.loading))
    }

    fun showProgressDialog() {
        if (pd?.isShowing == true) {
            pd?.dismiss()
        }
        pd?.show()
    }

    fun hideProgressDialog() {
        if (pd?.isShowing == true) {
            pd?.dismiss()
        }
    }

    open fun getFormatData(millSec: Long, format: String?): String? {
        return try {
            val cal = Calendar.getInstance()
            cal.timeInMillis = millSec
            val dateFormat =
                SimpleDateFormat(format, Locale.US)
            dateFormat.format(cal.time)
        } catch (e: Exception) {
            null
        }
    }

}