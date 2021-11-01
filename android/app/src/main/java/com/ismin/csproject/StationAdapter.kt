package com.ismin.csproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(private val stations : ArrayList<StationToView>) :
    RecyclerView.Adapter<StationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_station, parent, false)
        return StationViewHolder(row)
    }
    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val (ad_station,acces_recharge,accessibilite) = stations[position]

        holder.ad_station.text = ad_station
        holder.acces_recharge.text = acces_recharge
        holder.accessibilite.text = accessibilite
    }

    override fun getItemCount(): Int {
        return stations.size
    }

}