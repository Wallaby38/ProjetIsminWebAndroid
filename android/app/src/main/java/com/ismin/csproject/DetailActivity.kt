package com.ismin.csproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    val SERVER_BASE_URL = "https://project-jla-qja.cleverapps.io/"
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val stationService = retrofit.create(StationService::class.java)
    var station: Station? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id_station : String? = intent.getStringExtra("id_station")

        //Toast.makeText(this,id.toString(), Toast.LENGTH_SHORT).show()

        if (id_station != null && station != null) {
            loadStation(id_station)
            findViewById<TextView>(R.id.id_station).text = station!!.id_station
            findViewById<TextView>(R.id.acces_recharge).text = station!!.acces_recharge
            findViewById<TextView>(R.id.nbre_pdc).text = station!!.nbre_pdc.toString()
            findViewById<TextView>(R.id.region).text = station!!.region
            findViewById<TextView>(R.id.type_prise).text = station!!.type_prise
            findViewById<TextView>(R.id.ad_station).text = station!!.ad_station
            findViewById<TextView>(R.id.accessibilite).text = station!!.accessibilite
            findViewById<TextView>(R.id.puiss_max).text = station!!.puiss_max.toString()

        }
    }


    private fun loadStation(id:String) {
        stationService.getStation(id).enqueue(object : Callback<Station> {







            override fun onFailure(call: Call<Station>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Error when trying to fetch stations" + t.localizedMessage, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Station>, response: Response<Station>) {
                station = response.body()
            }


        })
    }
}