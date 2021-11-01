package com.ismin.csproject

class StationList {
    private val stations = HashMap<String, StationToView>()
    constructor() {
        stations["coucou"] = StationToView("Gardanne","prise", "24/24")
        stations["couco"] = StationToView("Gardanne","prise", "24/24")
        stations["coucu"] = StationToView("Gardanne","prise", "24/24")
        stations["couou"] = StationToView("Gardanne","prise", "24/24")
        stations["cocou"] = StationToView("Gardanne","prise", "24/24")
        stations["cucou"] = StationToView("Gardanne","prise", "24/24")
        stations["oucou"] = StationToView("Gardanne","prise", "24/24")
        stations["ucou"] = StationToView("Gardanne","prise", "24/24")
        stations["cou"] = StationToView("Gardanne","prise", "24/24")


    }

    fun getAllStations(): ArrayList<StationToView> {
        return ArrayList(stations.values.sortedBy { it.ad_station })
    }
}