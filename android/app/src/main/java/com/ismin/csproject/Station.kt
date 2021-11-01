package com.ismin.csproject

data class Station(
    val ylatitude: Double,
    val xlatitude: Double,
    val type_prise: String,
    val id_station: String,

    val accessibilite: String,
    val region: String,
    val ad_station: String,
    val puiss_max: Double,
    val acces_recharge: String,
    val nbre_pdc: Double
)
