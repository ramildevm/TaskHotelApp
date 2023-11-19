package com.example.taskhotelapp.data.remote.api

import com.example.taskhotelapp.data.remote.models.RoomBookingResponse
import com.example.taskhotelapp.data.remote.models.RoomsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoomBookingApi {
    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getRoomBookingDetails():Response<RoomBookingResponse>
}