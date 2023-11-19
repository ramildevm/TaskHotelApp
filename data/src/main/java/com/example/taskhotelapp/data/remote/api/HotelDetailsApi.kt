package com.example.taskhotelapp.data.remote.api

import com.example.taskhotelapp.data.remote.models.HotelDetailsResponse
import retrofit2.Response
import retrofit2.http.GET

interface HotelDetailsApi {
    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotelDetails():Response<HotelDetailsResponse>
}