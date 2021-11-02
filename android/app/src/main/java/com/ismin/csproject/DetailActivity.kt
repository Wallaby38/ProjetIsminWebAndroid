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
        }
    }
}