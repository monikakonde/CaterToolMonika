package com.example.catertool.activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.adapter.GetApiHnsUnitAdpater
import com.example.catertool.databinding.ActivitySalesCostTrackerBinding
import com.example.catertool.viewmodel.GetApiHnsUnitModel

class SalesCostTrackerActivity : BaseActivity() {
    private lateinit var binding: ActivitySalesCostTrackerBinding
    private var UserId = ""
    private var token = ""
    private var brand_id = ""
    private val viewModel3: GetApiHnsUnitModel by viewModels()
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySalesCostTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        token = PreferenceHelper.preferences["jwt", ""]
        brand_id = PreferenceHelper.preferences["brand_id", ""]
        UserId = PreferenceHelper.preferences["UserId", ""]

        binding.tabLayout.getTabAt(0)?.select()
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        InitToolbar()
        OnclickListn()
    }

    private fun OnclickListn() {
        initGetApiHnsUnit()
    }

    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        tvHeader?.setText("Sales/Cost Tracker")

        ivBackImg.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initGetApiHnsUnit() {
        showProgressDialog()
        viewModel3.performGetApiHnsUnit(token,"15","*"
        ).observe(this) {
            if (it != null) {
                binding.recycleView.setAdapter(
                    GetApiHnsUnitAdpater(it))
                hideProgressDialog()
            } else {
                hideProgressDialog()
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}