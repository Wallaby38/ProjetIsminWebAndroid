package com.ismin.csproject

class StationList {
    private val stations = HashMap<String, Station>()
    constructor() {

        stations["coucou"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["couco"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["coucu"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["couou"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["cocou"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["cucou"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["oucou"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["ucou"] = Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)
        stations["cou"] =Station(184.5,184.5, "24/24","3425232523","24/24","PACA","Gardanne",184.5,"acces recharge",13.5)


    }

    fun getAllStationsToView() : ArrayList<StationToView> {
        val stationsToView  : ArrayList<StationToView> = ArrayList()
        for(it in getAllStations()) {
            stationsToView.add(StationToView(it.id_station,it.ad_station,it.acces_recharge,it.accessibilite))
        }
        return stationsToView
    }

    fun getAllStations(): ArrayList<Station> {
        return ArrayList(stations.values.sortedBy { it.ad_station })
    }
}