package com.ismin.csproject

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationViewHolder(rootView: View,listener: StationAdapter.onItemClickListener): RecyclerView.ViewHolder(rootView) {
    var ad_station = rootView.findViewById<TextView>(R.id.ad_station)
    var acces_recharge = rootView.findViewById<TextView>(R.id.acces_recharge)
    var accessibilite = rootView.findViewById<TextView>(R.id.accessibilite)
    var id = rootView.findViewById<TextView>(R.id.id_station)
    var favorite = rootView.findViewById<ImageView>(R.id.favorite)


    init{
        itemView.setOnClickListener {
            listener.onItemClick(id.text.toString())
        }
    }
}