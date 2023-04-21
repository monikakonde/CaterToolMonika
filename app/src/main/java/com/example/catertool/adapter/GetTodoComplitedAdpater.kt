package com.example.catertool.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catertool.R
import com.example.catertool.model.TodoComplited
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class GetTodoComplitedAdpater(
    private var TodoComplitedArrayList: ArrayList<TodoComplited.Data>,
    private val onitemClickeLisner: OnitemClickeLisner
) :
    RecyclerView.Adapter<GetTodoComplitedAdpater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todolist, parent, false)
        return ViewHolder(view, onitemClickeLisner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos: Int = position
        try {
            holder.tvtitle.text = TodoComplitedArrayList[pos].attributes.title
            val dtStart = TodoComplitedArrayList[pos].attributes.deadlineDateTime
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
                TodoComplitedArrayList[pos].id,
                TodoComplitedArrayList[pos].attributes.title,
                TodoComplitedArrayList[pos].attributes.notes,
                TodoComplitedArrayList[pos].attributes.deadlineDateTime,
                "ImgDelate","Complited"
            )
        }

        holder.TvDone.setOnClickListener {
            onitemClickeLisner.onclick(
                TodoComplitedArrayList.get(pos).id,
                TodoComplitedArrayList.get(pos).attributes.title,
                TodoComplitedArrayList.get(pos).attributes.notes,
                TodoComplitedArrayList.get(pos).attributes.deadlineDateTime,
                "NonImgDelate","Complited"
            )
        }

        holder.Rb_checkboox.setOnClickListener {
            val filtered = TodoComplitedArrayList.filter { it.attributes.isSelected }
            if (filtered.isNotEmpty()) {
                filtered.first().attributes.isSelected = false
                notifyDataSetChanged()
            }
            TodoComplitedArrayList[pos].attributes.isSelected = true
            holder.ImgDate.visibility = View.VISIBLE
            holder.TvDone.visibility = View.GONE
        }
        holder.Rb_checkboox.isChecked = TodoComplitedArrayList[pos].attributes.isSelected
        if (TodoComplitedArrayList[pos].attributes.isSelected) {
            holder.ImgDate.visibility = View.VISIBLE
            holder.TvDone.visibility = View.GONE
        } else {
            holder.ImgDate.visibility = View.GONE
            holder.TvDone.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return TodoComplitedArrayList.size
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