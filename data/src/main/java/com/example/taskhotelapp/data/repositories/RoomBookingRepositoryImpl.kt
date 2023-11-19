package com.example.taskhotelapp.data.repositories

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.data.remote.api.RoomBookingApi
import com.example.taskhotelapp.data.remote.mappers.toModel
import com.example.taskhotelapp.domain.models.RoomBooking
import com.example.taskhotelapp.domain.repositories.RoomBookingRepository

class RoomBookingRepositoryImpl(
    private val api:RoomBookingApi
):RoomBookingRepository {
    override suspend fun getRoomBooking(): Resource<RoomBooking> {
        val response = api.getRoomBookingDetails()
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success( resultResponse.toModel())
            }
        }
        return Resource.Error(response.message())
    }
}