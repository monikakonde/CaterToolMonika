package com.example.catertool.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.adapter.GetEventListAdpater
import com.example.catertool.databinding.ActivityEventListBinding
import com.example.catertool.viewmodel.GetApiSelaseRecordEventListModel

class EventListActivity : BaseActivity() {
    private lateinit var binding: ActivityEventListBinding
    private var Ids = ""
    private var unitName = ""
    private var UserId = ""
    private var token = ""
    private var brand_id = ""
    private val viewModel3: GetApiSelaseRecordEventListModel by viewModels()
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, SalesCostTrackerActivity::class.java)
        startActivity(intent)
        finish() }

       override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityEventListBinding.inflate(layoutInflater)
            setContentView(binding.root)

            token = PreferenceHelper.preferences["jwt", ""]
            brand_id = PreferenceHelper.preferences["brand_id", ""]
            UserId = PreferenceHelper.preferences["UserId", ""]


           Ids = intent.getIntExtra("Id",0).toString()
           unitName = intent.getStringExtra("unitName").toString()
           Log.d("onCreate_fhvfhfqw", "onCreate: "+Ids)

            binding.recycleView.layoutManager = LinearLayoutManager(this)
            InitToolbar()
            OnclickListn()
           binding.ivAdd.setOnClickListener {
               val intent = Intent(this, AddSalesRecordFormActivity::class.java)
               intent.putExtra("unitName", unitName)
               intent.putExtra("Id", Ids)
               startActivity(intent)
               finish()
           }
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
            tvHeader?.setText(unitName)

            ivBackImg.setOnClickListener {
                val intent = Intent(this, SalesCostTrackerActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        private fun initGetApiHnsUnit() {
            showProgressDialog()
            viewModel3.performGetSealseEventList(token,"*",Ids).observe(this) {
                if (it != null) {

                    if (it.data.size>0){
                        binding.LlUers.visibility= View.VISIBLE

                    }else{
                        binding.LlUers.visibility= View.GONE
                    }
                    binding.recycleView.setAdapter(
                        GetEventListAdpater(it)
                    )
                    hideProgressDialog()
                } else {
                    hideProgressDialog()
                    //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }