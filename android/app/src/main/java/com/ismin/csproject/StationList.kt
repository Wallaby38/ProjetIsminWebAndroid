package com.ismin.csproject

import android.util.Log
import android.widget.Toast

class StationList {
    private val stations = HashMap<String, StationToView>()
    constructor() {




    }



    fun getAllStations(): ArrayList<StationToView> {
        return ArrayList(stations.values.filter { s -> s.id_station != null && s.ad_station != null && s.accessibilite != null && s.acces_recharge != null}.sortedBy { it.id_station })
    }

    fun getStation(id: String) : StationToView? {
        return stations[id]
    }

    fun addStation(station: StationToView): Unit {
        stations[station.id_station] = station
    }

    fun setBookMarked(station:Station): Unit {
        stations[station.id_station] = StationToView(station.id_station,station.ad_station,station.acces_recharge,station.accessibilite,station.ylatitude,station.xlongitude,station.bookmarked)
    }

    fun empty() {
        stations.clear()
    }


}