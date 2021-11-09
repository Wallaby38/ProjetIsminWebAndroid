package com.ismin.csproject

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface StationService {
    @GET("station")
    fun getStations() : Call<List<StationToView>>
    @GET("station/{id_station}")
    fun getStation(@Path("id_station") id_station: String): Call<Station>
    @PUT("station/{id_station}")
    fun changeBookmarked(@Path("id_station") id_station: String,@Body station: Station): Call<Unit>

}