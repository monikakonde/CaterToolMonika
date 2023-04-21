package com.example.catertool.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.databinding.ActivityDashboardBinding
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle

class DashboardActivity : AppCompatActivity() {
    private val sharedPrefFile = "Sharedpreference"
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inits()
        OnclickListners()
        var token = PreferenceHelper.preferences["jwt", ""]
        var companyName = PreferenceHelper.preferences["companyName", ""]
        binding.tvCompany.setText(companyName)

        binding.ImgNotification.setOnClickListener {
            val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun OnclickListners() {
        binding.LlCompanyDetails.setOnClickListener {
            val intent = Intent(this, CompanyDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.LlOpeningClosingChecks.setOnClickListener {
            val intent = Intent(this, OpeninigClosingChecksActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.LlHelthAndSefty.setOnClickListener {
            val intent = Intent(this, AddUnitActivity::class.java)
            startActivity(intent)
            finish()
        }

//        binding.LlSalesCostTracker.setOnClickListener {
//            val intent = Intent(this, SalesCostTrackerActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        binding.LlTodo.setOnClickListener {
            val intent = Intent(this, ToDoActvity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun inits() {
        setSupportActionBar(binding.toolbar)
        val drawerToggle = DuoDrawerToggle(
            this, binding.drawer, binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawer.setDrawerListener(drawerToggle)


        //getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));
        drawerToggle.syncState()
        val conentview = binding.drawer.contentView
        val menuView = binding.drawer.menuView

        binding.toolbar.title = ""
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_navigation)
        binding.RlLogout.setOnClickListener {
            val sharedPreferences: SharedPreferences =
                this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}