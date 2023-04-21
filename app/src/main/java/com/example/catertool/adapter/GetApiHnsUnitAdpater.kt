package com.example.catertool.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catertool.R
import com.example.catertool.activity.EventListActivity
import com.example.catertool.model.HnsUnits

class GetApiHnsUnitAdpater(private var arrayList: HnsUnits) :
    RecyclerView.Adapter<GetApiHnsUnitAdpater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_selas_cost_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var pos: Int = position;

        holder.tvTitle.text = arrayList.data.get(pos).attributes.unitName
        holder.tvFoodName.text = arrayList.data.get(pos).attributes.typeOfFood
        holder.tvVehicle.text= arrayList.data.get(pos).attributes.unitType.data?.attributes?.Name


        holder.img_forword.setOnClickListener { view ->
        view?.context?.startActivity(Intent(view?.context, EventListActivity::class.java)
                    .putExtra("Id", arrayList.data.get(pos).id)
                    .putExtra("unitName", arrayList.data.get(pos).attributes.unitName))
            Log.d("onBindViewHolder_test", "onBindViewHolder: "+arrayList.data.get(pos).id)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var tvTitle: TextView
        lateinit var tvFoodName: TextView
        lateinit var tvVehicle: TextView
        lateinit var img_forword: ImageView
        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvFoodName = itemView.findViewById(R.id.tvFoodName)
            tvVehicle = itemView.findViewById(R.id.tvVehicle)
            img_forword = itemView.findViewById(R.id.img_forword)

        }
    }
}