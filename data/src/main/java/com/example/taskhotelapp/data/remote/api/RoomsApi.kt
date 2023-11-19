package com.example.taskhotelapp.data.remote.api

import com.example.taskhotelapp.data.remote.models.RoomsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoomsApi {
    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms():Response<RoomsResponse>
}