package com.example.catertool.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catertool.R
import com.example.catertool.model.GetAllTodoScheduled


class GetTodoListSchduledAdpater(private var arrayList: GetAllTodoScheduled) :
    RecyclerView.Adapter<GetTodoListSchduledAdpater.ViewHolder>() {
    private var lastChecked: CheckBox? = null
    private var lastCheckedPos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todolist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var pos: Int = position;

        holder.tvtitle.text = arrayList.data.get(pos).attributes.title
        holder.tvtitle.text = arrayList.data.get(pos).attributes.title

        holder.Rb_checkboox.setOnClickListener {
            holder.ImgDate.visibility=View.VISIBLE
            holder.TvDone.visibility=View.VISIBLE
        }

//        holder.Rb_checkboox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//
//            }
//            notifyDataSetChanged()
//        })

    }

    override fun getItemCount(): Int {
        return arrayList.data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var tvtitle: TextView
        lateinit var Rb_checkboox: RadioButton
        lateinit var ImgDate: ImageView
        lateinit var TvDone: TextView

        init {
            tvtitle = itemView.findViewById(R.id.tvtitle)
            Rb_checkboox = itemView.findViewById(R.id.Rb_checkboox)
            ImgDate = itemView.findViewById(R.id.ImgDate)
            TvDone = itemView.findViewById(R.id.TvDone)


        }
    }
}