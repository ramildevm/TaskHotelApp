package com.example.taskhotelapp.domain.repositories

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.models.Room
import com.example.taskhotelapp.domain.models.RoomBooking

interface RoomBookingRepository {
    suspend fun getRoomBooking(): Resource<RoomBooking>
}