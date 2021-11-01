package com.ismin.csproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val stations = StationList()

    private val adapter = StationAdapter(stations.getAllStations())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rcvBooks = findViewById<RecyclerView>(R.id.a_main_rcv_books)
        rcvBooks.layoutManager = LinearLayoutManager(this)
        rcvBooks.adapter = adapter
    }
}