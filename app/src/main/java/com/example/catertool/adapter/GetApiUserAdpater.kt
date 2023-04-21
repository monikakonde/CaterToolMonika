package com.example.catertool.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catertool.R
import com.example.catertool.activity.MainActivity
import com.example.catertool.activity.UserDetailsScreen
import com.example.catertool.model.GetAllUserOfBrand

class GetApiUserAdpater(private var arrayList: GetAllUserOfBrand) :
    RecyclerView.Adapter<GetApiUserAdpater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var pos: Int = position;

        holder.title.text = arrayList.get(pos).firstName+" "+arrayList.get(pos).lastName
        holder.tv_MobileNuber.text = arrayList.get(pos).mobile

        holder.img_next.setOnClickListener { view ->

            view?.context?.startActivity(Intent(view?.context, UserDetailsScreen::class.java)
                    .putExtra("Email", arrayList.get(pos).email)
                    .putExtra("Mobile",arrayList.get(pos).mobile)
                    .putExtra("UserName", arrayList.get(pos).username)
                    .putExtra("ids", arrayList.get(pos).id)
            )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        lateinit var title: TextView
        lateinit var tv_MobileNuber: TextView
        lateinit var img_next: ImageView


        init {
            title = itemView.findViewById(R.id.tv_UserName)
            tv_MobileNuber = itemView.findViewById(R.id.tv_MobileNuber)
            img_next = itemView.findViewById(R.id.img_next)
        }
    }
}