package com.example.catertool.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catertool.R
import com.example.catertool.activity.SelaseEventDetailsActivity
import com.example.catertool.model.EventSelaseRecordList

class GetEventListAdpater(private var arrayList: EventSelaseRecordList) :
    RecyclerView.Adapter<GetEventListAdpater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_event_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var pos: Int = position;

        holder.tvAllDate.text = arrayList.data.get(pos).attributes.eventDate
        holder.tvEventName.text = arrayList.data.get(pos).attributes.eventName
        holder.tvDate.text = arrayList.data.get(pos).attributes.eventDate
        holder.tvTotal.text = "£ "+arrayList.data.get(pos).attributes.cash.toString()
        holder.tvUnit.text= arrayList.data.get(pos).attributes.hns_unit.data.attributes.unitName


        holder.tvTotal.setOnClickListener { view ->
        view?.context?.startActivity(Intent(view?.context, SelaseEventDetailsActivity::class.java)
                    .putExtra("Id", arrayList.data.get(pos).attributes.eventName)
                    .putExtra("EventDate", arrayList.data.get(pos).attributes.eventDate)
                    .putExtra("EventName", arrayList.data.get(pos).attributes.eventName)
                    .putExtra("Cash", arrayList.data.get(pos).attributes.cash)
                    .putExtra("Card", arrayList.data.get(pos).attributes.card)
                    .putExtra("Other", arrayList.data.get(pos).attributes.other)
                    .putExtra("UnitName",  arrayList.data.get(pos).attributes.hns_unit.data.attributes.unitName)

        )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var tvAllDate: TextView
        lateinit var tvEventName: TextView
        lateinit var tvUnit: TextView
        lateinit var tvDate: TextView
        lateinit var tvTotal: TextView
        init {
            tvAllDate = itemView.findViewById(R.id.tvAllDate)
            tvEventName = itemView.findViewById(R.id.tvEventName)
            tvUnit = itemView.findViewById(R.id.tvUnit)
            tvDate = itemView.findViewById(R.id.tvDate)
            tvTotal = itemView.findViewById(R.id.tvTotal)
        }
    }
}