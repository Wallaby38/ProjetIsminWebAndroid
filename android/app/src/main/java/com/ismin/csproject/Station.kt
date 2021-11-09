package com.ismin.csproject

import java.io.Serializable


data class Station(
    val id_station: String,
    val ylatitude: Double,
    val xlongitude: Double,
    val type_prise: String,

    val accessibilite: String,
    val region: String,
    val ad_station: String,
    val puiss_max: Double,
    val acces_recharge: String,
    val nbre_pdc: Double,
    var bookmarked: Boolean
) : Serializable
