package com.example.catertool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.catertool.R
import com.example.catertool.model.AllCommenOpningCheck
import com.example.catertool.viewmodel.UpdateAllChecksViewModel

class GetApiAllOpningCheckAdpater(private var arrayList: AllCommenOpningCheck,
                                  private val onitemClickeLisner:OnitemClickeLisner) :
    RecyclerView.Adapter<GetApiAllOpningCheckAdpater.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_check_list, parent, false)
        return ViewHolder(view,onitemClickeLisner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var pos: Int = position;
        holder.title.text = arrayList.data.get(pos).attributes.checkName
        holder.delete_task.setOnClickListener {
            onitemClickeLisner.onclick(arrayList.data.get(pos).id)
        }

    }

    override fun getItemCount(): Int {
        return arrayList.data.size
    }

    class ViewHolder(itemView: View,onitemClickeLisner: OnitemClickeLisner) : RecyclerView.ViewHolder(itemView) {
        lateinit var title: TextView
        lateinit var delete_task: RelativeLayout

        init {
            title = itemView.findViewById(R.id.Tvtitle)
            delete_task = itemView.findViewById(R.id.delete_task)

        }
    }

    interface OnitemClickeLisner{
        fun onclick(position: Int)
        fun longclike(position: Int)
    }
}