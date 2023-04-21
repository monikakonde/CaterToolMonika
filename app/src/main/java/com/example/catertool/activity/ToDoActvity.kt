package com.example.catertool.activity

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R
import com.example.catertool.adapter.*
import com.example.catertool.databinding.ActivityToDoActvityBinding
import com.example.catertool.model.GetAllToDoListToday
import com.example.catertool.model.GetAllTodoScheduled
import com.example.catertool.model.TodoComplited
import com.example.catertool.model.UpdateDoneTodoReqest
import com.example.catertool.viewmodel.*
import com.google.android.material.tabs.TabLayout
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ToDoActvity : BaseActivity(),GetTodoListTodayAdpater.OnitemClickeLisner,GetTodoSchduledAdpater.OnitemClickeLisner,GetTodoComplitedAdpater.OnitemClickeLisner  {
    private lateinit var binding: ActivityToDoActvityBinding
    private val viewModel3: GetApiTodoTodayListModel by viewModels()
    private val viewModel1: GetApiTodoSchduledListModel by viewModels()
    private val viewModel2: GetApiTodoComplitedListModel by viewModels()
    private val viewModel4: DeleteTodoViewModel by viewModels()
    private val viewModel5: TodoDoneModel by viewModels()
    private var UserId = ""
    private var token = ""
    private var brand_id = ""
    private var todayAsString=""
    private var tomorrowAsString=""
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_actvity)
        binding = ActivityToDoActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        token = PreferenceHelper.preferences["jwt", ""]
        brand_id = PreferenceHelper.preferences["brand_id", ""]
        UserId = PreferenceHelper.preferences["UserId", ""]
        val c: Date = Calendar.getInstance().getTime()
        println("Current time => $c")

        val df = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate: String = df.format(c)

        /////////////////
        val calendar = Calendar.getInstance()
        val today = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

      todayAsString = dateFormat.format(today)
      tomorrowAsString = dateFormat.format(tomorrow)
      initGetTodoTodayList(todayAsString, tomorrowAsString)

    }

    private fun onClicks() {

        val tvHeader = findViewById<AppCompatTextView>(R.id.tvHeader)
        tvHeader.text = "Add To Do"

        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        ivBackImg.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.ivAdd.setOnClickListener {
            val intent = Intent(this, AddToList::class.java)
            startActivity(intent)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                val position = tab!!.position
                Log.d("onTabSelected", "onTabSelected: "+position)

                if (position == 0) {
                    initGetTodoTodayList(todayAsString, tomorrowAsString)
                } else if (position == 1){
                    initGetTodoSchdeuledList(todayAsString, tomorrowAsString)
                }else if (position == 2){
                    initGetTodoCompletedList(todayAsString, tomorrowAsString)
                }


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val position = tab!!.position

                if (position == 1) {

                } else {

                }
            }

        })

    }

    private fun initGetTodoTodayList(todayAsString: String, tomorrowAsString: String) {
        showProgressDialog()
        viewModel3.performGetTodoTodayList(
            token, UserId, todayAsString, tomorrowAsString, "Completed", "*"
        ).observe(this) {
            if (it != null) {

                binding.recycleView.setAdapter(GetTodoListTodayAdpater(it.data as ArrayList<GetAllToDoListToday.Data>,this))
                hideProgressDialog()
            } else {
                hideProgressDialog()
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initGetTodoSchdeuledList(todayAsString: String, tomorrowAsString: String) {
        showProgressDialog()
        viewModel1.performGetSchduledList(token, UserId, tomorrowAsString, "Completed", "*").observe(this) {
            if (it != null) {
                binding.recycleView.setAdapter(GetTodoSchduledAdpater(it.data as ArrayList<GetAllTodoScheduled.Data>,this))
                hideProgressDialog()
            } else {
                hideProgressDialog()
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initGetTodoCompletedList(todayAsString: String, tomorrowAsString: String) {
        showProgressDialog()
        viewModel2.performGetComplitedList(token, UserId, todayAsString, "Completed", "*"
        ).observe(this) {
            if (it != null) {
                binding.recycleView.setAdapter(GetTodoComplitedAdpater(it.data as ArrayList<TodoComplited.Data>,this))
                hideProgressDialog()
            } else {
                hideProgressDialog()
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onclick(
        position: Int,
        title: String,
        notes: String,
        deadlineDateTime: String,
        tag: String,
        Type: String
    ) {
        Log.d("onclick", "onclick: "+ tag)
        if (tag.equals("ImgDelate")){
            initDelateTodo(position.toString(),Type)
        }else{
            TodoDone(position,title,notes,deadlineDateTime,Type)
        }
    }
    private fun initDelateTodo(position: String, Type: String) {
        viewModel4.performDeleteTodo(token,position).observe(this) {
            if (it != null) {
                if (Type.equals("Todo")){
                    initGetTodoTodayList(todayAsString, tomorrowAsString)
                }else if (Type.equals("Schduled")){
                    initGetTodoSchdeuledList(todayAsString, tomorrowAsString)
                }else if (Type.equals("Complited")){
                    initGetTodoCompletedList(todayAsString, tomorrowAsString)
                }
            } else {
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun TodoDone(
        position: Int,
        title: String,
        notes: String,
        deadlineDateTime: String,
        Type: String
    ) {

        val data: UpdateDoneTodoReqest.Data = UpdateDoneTodoReqest.Data(
            UserId,deadlineDateTime,notes,"Completed",title)
        val updateDoneTodoReqest: UpdateDoneTodoReqest = UpdateDoneTodoReqest(data)

        viewModel5.performDoneTodo(token,position.toString(),updateDoneTodoReqest).observe(this) {
            if (it != null) {
                if (Type.equals("Todo")){
                    initGetTodoTodayList(todayAsString, tomorrowAsString)
                }else if (Type.equals("Schduled")){
                    initGetTodoSchdeuledList(todayAsString, tomorrowAsString)
                }else if (Type.equals("Complited")){
                    initGetTodoCompletedList(todayAsString, tomorrowAsString)
                }
            } else {
              //  Toast.makeText(applicationContext, "Faile", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun longclike(position: Int) {
        TODO("Not yet implemented")
    }

}