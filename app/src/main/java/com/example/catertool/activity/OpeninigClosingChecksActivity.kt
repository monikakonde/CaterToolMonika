package com.example.catertool.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.Pref.PreferenceHelper.get
import com.example.catertool.R

import com.example.catertool.adapter.GetApiAllOpningCheckAdpater
import com.example.catertool.databinding.ActivityOpeninigClosingChecksBinding
import com.example.catertool.model.AddChecksReqest
import com.example.catertool.util.RecyclerTouchListener
import com.example.catertool.util.RecyclerTouchListener.OnRowClickListener
import com.example.catertool.util.RecyclerTouchListener.OnSwipeOptionsClickListener
import com.example.catertool.viewmodel.DeleteCommonCheckViewModel
import com.example.catertool.viewmodel.GetApiAllCommenClosingCheckModel
import com.example.catertool.viewmodel.GetApiAllCommenOpningCheckModel
import com.example.catertool.viewmodel.UpdateAllChecksViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout


class OpeninigClosingChecksActivity : AppCompatActivity(),GetApiAllOpningCheckAdpater.OnitemClickeLisner {
    private lateinit var binding: ActivityOpeninigClosingChecksBinding
    private var ed_Email = ""
    private var UserId = ""
    private var token = "";
    private var brand_id = "";
    private var AddUser = "";
    private val viewModel3: UpdateAllChecksViewModel by viewModels()
    private val viewModel: GetApiAllCommenOpningCheckModel by viewModels()
    private val viewModel2: GetApiAllCommenClosingCheckModel by viewModels()
    private val viewModel4: DeleteCommonCheckViewModel by viewModels()
    //Create Object recyclerTouchListener
    private lateinit var recyclerTouchListener: RecyclerTouchListener
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpeninigClosingChecksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        token = PreferenceHelper.preferences["jwt", ""]
        brand_id = PreferenceHelper.preferences["brand_id", ""]
        UserId = PreferenceHelper.preferences["UserId", ""]

        binding.tabLayout.getTabAt(0)?.select()

        recyclerTouchListener = RecyclerTouchListener(this, binding.recycleView)
        recyclerTouchListener.setClickable(object : OnRowClickListener {
            override fun onRowClicked(position: Int) {

                Toast.makeText(
                    applicationContext, "Click  $position", Toast.LENGTH_SHORT).show()
            }

            override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
        })
            .setSwipeOptionViews(R.id.delete_task)
            .setSwipeable(R.id.rowFG, R.id.rowBG,
                OnSwipeOptionsClickListener { viewID, position ->
                    when (viewID) {
                        R.id.delete_task -> {
                        //    Toast.makeText(applicationContext, "Item deleted $position", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        binding.recycleView.addOnItemTouchListener(recyclerTouchListener)
        initGetApiOpningChecks()
        InitToolbar()
        ToolBarOnClike()
    }

    private fun ToolBarOnClike() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                if (position == 1) {
                    initGetApiClosingChecks()
                } else {
                    initGetApiOpningChecks()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val position = tab!!.position

                if (position == 1) {
                    binding.svOpeningChecks.visibility = View.GONE

                } else {
                    binding.svOpeningChecks.visibility = View.VISIBLE

                }
            }

        })
    }

    private fun InitToolbar() {
        val ivBackImg = findViewById<AppCompatImageView>(R.id.ivBackImg)
        var tvHeader: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvHeader)
        var uper: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvOne)
        var bello: AppCompatTextView? = findViewById<AppCompatTextView>(R.id.tvTwo)
        var LlScreens: LinearLayout? = findViewById<LinearLayout>(R.id.LlScreens)
        tvHeader?.setText("Openinig & Closing Checks")

        binding.recycleView.layoutManager = LinearLayoutManager(this)

        ivBackImg.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvAddOpeningChecks.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.item_add_opning_check, null)
            val btnClose = view.findViewById<ImageView>(R.id.ivClose)
            val btnChecks = view.findViewById<TextView>(R.id.btnChecksadd)
            val etOpeningChecks = view.findViewById<EditText>(R.id.etOpeningChecks)
            dialog.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            btnClose.setOnClickListener {
                dialog.dismiss()
            }

            btnChecks.setOnClickListener {
                val tab_position: Int = binding.tabLayout.getSelectedTabPosition()
                Log.d("onCreate", "onCreate: checkpostion" + tab_position)
                if (tab_position.equals(0)) {
                    var isOpen = 1
                    var isclose = 0
                    AddCheck(etOpeningChecks.text.toString(), isOpen, isclose, dialog)
                } else {
                    var isOpen = 0
                    var isclose = 1
                    AddCheck(etOpeningChecks.text.toString(), isOpen, isclose, dialog)
                }
                Log.d("InitToolbar", "InitToolbar: test" + etOpeningChecks.text.toString())
            }

            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
    }

    private fun initGetApiOpningChecks() {
        viewModel.performGetApiAllCommonPoningCheck(
            token,
            "*",
            brand_id, "1"
        ).observe(this) {
            if (it != null) {
                binding.recycleView.setAdapter(
                    GetApiAllOpningCheckAdpater(it,this)
                )
            } else {
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initGetApiClosingChecks() {
        viewModel2.performGetApiAllCommonConingCheck(
            token,
            "*",
            brand_id, "1"
        ).observe(this) {
            if (it != null) {
                binding.recycleView.setAdapter(
                    GetApiAllOpningCheckAdpater(it,this)
                )
            } else {
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun AddCheck(
        checks_data: String,
        isOpen: Int,
        isclose: Int,
        dialog: BottomSheetDialog
    ) {
        val data: AddChecksReqest.Data =
            AddChecksReqest.Data(brand_id.toInt(), checks_data, isclose, isOpen)
        val addChecksReqest: AddChecksReqest = AddChecksReqest(data)

        viewModel3.performUpdateAllChecks(token, addChecksReqest).observe(this) {
            if (it != null) {
                if (isOpen.equals(1)) {
                    initGetApiOpningChecks()
                    dialog.dismiss()
                } else {
                    initGetApiClosingChecks()
                    dialog.dismiss()
                }
            } else {
                //    Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initDelateCommonCheck(position: String) {
        viewModel4.performDeleteCommonCheck(
            token,
            position
        ).observe(this) {
            if (it != null) {
                val tab_position: Int = binding.tabLayout.getSelectedTabPosition()
                if (tab_position.equals(0)) {
                    initGetApiOpningChecks()
                } else {
                    initGetApiClosingChecks()
                }
            } else {
                //  Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        binding.recycleView.addOnItemTouchListener(recyclerTouchListener)
    }

    override fun onclick(position: Int) {
        initDelateCommonCheck(position.toString())
     //   Toast.makeText(this, "pass   "+position, Toast.LENGTH_SHORT).show()
    }

    override fun longclike(position: Int) {
        TODO("Not yet implemented")
    }
}