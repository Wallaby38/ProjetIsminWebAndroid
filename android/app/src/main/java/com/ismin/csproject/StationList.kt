package com.ismin.csproject

import android.util.Log
import android.widget.Toast

class StationList {
    private val stations = HashMap<String, Station>()
    constructor() {




    }

    fun getAllStationsToView() : ArrayList<StationToView> {
        val stationsToView  : ArrayList<StationToView> = ArrayList<StationToView>()


        for(it in getAllStations()) {
           val stationToView : StationToView = StationToView(it.id_station,it.ad_station,it.acces_recharge,it.accessibilite)
           stationsToView.add(stationToView)
        }
        return stationsToView
    }

    fun getAllStations(): ArrayList<Station> {
        return ArrayList(stations.values.filter { s -> s.id_station != null && s.ad_station != null && s.accessibilite != null && s.acces_recharge != null}.sortedBy { it.id_station })
    }

    fun getStation(id: String) : Station? {
        return stations[id]
    }

    fun addStation(station: Station): Unit {
        stations[station.id_station] = station
    }


}