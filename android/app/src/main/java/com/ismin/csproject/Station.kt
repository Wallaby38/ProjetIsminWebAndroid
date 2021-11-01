package com.ismin.csproject

data class Station(
    val ylatitude : Float,
    val xlatitude : Float,
    val type_prise : String,
    val id_station : String,

    val accessibilite: String,
    val region : String,
    val ad_station : String,
    val puiss_max: Float,
    val acces_recharge: String,
    val nbre_pdc: Float
)
