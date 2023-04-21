package com.example.catertool.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catertool.R
import com.example.catertool.model.GetAllTodoScheduled
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class GetTodoSchduledAdpater(
    private var GetAllTodoScheduledArrayList: ArrayList<GetAllTodoScheduled.Data>,
    private val onitemClickeLisner: OnitemClickeLisner
) :
    RecyclerView.Adapter<GetTodoSchduledAdpater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todolist, parent, false)
        return ViewHolder(view, onitemClickeLisner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos: Int = position
        try {
            holder.tvtitle.text = GetAllTodoScheduledArrayList[pos].attributes.title
            val dtStart = GetAllTodoScheduledArrayList[pos].attributes.deadlineDateTime
            val outputFormat = SimpleDateFormat("dd MMM yyyy - hh:mm a", Locale.getDefault())
            //  9 Nov 2022 - 10:00 AM
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

            //  2023-04-13T09:15:37.000Z
            val date: Date = inputFormat.parse(dtStart)

            holder.tvDateTime.text = outputFormat.format(date)
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

        holder.ImgDate.setOnClickListener {
            onitemClickeLisner.onclick(
                GetAllTodoScheduledArrayList[pos].id,
                GetAllTodoScheduledArrayList[pos].attributes.title,
                GetAllTodoScheduledArrayList[pos].attributes.notes,
                GetAllTodoScheduledArrayList[pos].attributes.deadlineDateTime,
                "ImgDelate","Schduled"
            )
        }

        holder.TvDone.setOnClickListener {
            onitemClickeLisner.onclick(
                GetAllTodoScheduledArrayList.get(pos).id,
                GetAllTodoScheduledArrayList.get(pos).attributes.title,
                GetAllTodoScheduledArrayList.get(pos).attributes.notes,
                GetAllTodoScheduledArrayList.get(pos).attributes.deadlineDateTime,
                "NonImgDelate","Schduled"
            )
        }

        holder.Rb_checkboox.setOnClickListener {
            val filtered = GetAllTodoScheduledArrayList.filter { it.attributes.isSelected }
            if (filtered.isNotEmpty()) {
                filtered.first().attributes.isSelected = false
                notifyDataSetChanged()
            }
            GetAllTodoScheduledArrayList[pos].attributes.isSelected = true
            holder.ImgDate.visibility = View.VISIBLE
            holder.TvDone.visibility = View.VISIBLE
        }
        holder.Rb_checkboox.isChecked = GetAllTodoScheduledArrayList[pos].attributes.isSelected
        if (GetAllTodoScheduledArrayList[pos].attributes.isSelected) {
            holder.ImgDate.visibility = View.VISIBLE
            holder.TvDone.visibility = View.VISIBLE
        } else {
            holder.ImgDate.visibility = View.GONE
            holder.TvDone.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return GetAllTodoScheduledArrayList.size
    }

    class ViewHolder(itemView: View, onitemClickeLisner: OnitemClickeLisner) :
        RecyclerView.ViewHolder(itemView) {

        lateinit var tvtitle: TextView
        lateinit var Rb_checkboox: RadioButton
        lateinit var ImgDate: ImageView
        lateinit var TvDone: TextView
        lateinit var tvDateTime: TextView

        init {
            tvtitle = itemView.findViewById(R.id.tvtitle)
            Rb_checkboox = itemView.findViewById(R.id.Rb_checkboox)
            ImgDate = itemView.findViewById(R.id.ImgDate)
            TvDone = itemView.findViewById(R.id.TvDone)
            tvDateTime = itemView.findViewById(R.id.tvDateTime)


        }
    }

    interface OnitemClickeLisner {
        fun onclick(
            position: Int,
            title: String,
            notes: String,
            deadlineDateTime: String,
            s: String,
            s1: String
        )

        fun longclike(position: Int)
    }
}