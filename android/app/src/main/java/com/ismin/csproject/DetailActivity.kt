package com.ismin.csproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val station :Station? = intent.getSerializableExtra("station") as Station

        //Toast.makeText(this,id.toString(), Toast.LENGTH_SHORT).show()

        if (station != null) {
            findViewById<TextView>(R.id.id_station).text = station.id_station
            findViewById<TextView>(R.id.acces_recharge).text = station.acces_recharge
            findViewById<TextView>(R.id.nbre_pdc).text = station.nbre_pdc.toString()
            findViewById<TextView>(R.id.region).text = station.region
            findViewById<TextView>(R.id.type_prise).text = station.type_prise
            findViewById<TextView>(R.id.ad_station).text = station.ad_station
            findViewById<TextView>(R.id.type_prise).text = station.type_prise
            findViewById<TextView>(R.id.puiss_max).text = station.puiss_max.toString()

        }
    }
}