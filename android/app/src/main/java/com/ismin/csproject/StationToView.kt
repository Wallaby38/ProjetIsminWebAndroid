package com.ismin.csproject

import java.io.Serializable

data class StationToView(val id_station: String,val ad_station: String,val acces_recharge: String, val accessibilite: String ,val ylatitude: Double,val xlongitude: Double,) : Serializable {}
