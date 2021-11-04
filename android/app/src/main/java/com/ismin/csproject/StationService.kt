package com.ismin.csproject

import retrofit2.Call
import retrofit2.http.GET

interface StationService {
    @GET("station")
    fun getStations() : Call<List<Station>>
}