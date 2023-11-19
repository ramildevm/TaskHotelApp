package com.example.taskhotelapp.domain.use_cases

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.models.RoomBooking
import com.example.taskhotelapp.domain.repositories.HotelDetailsRepository
import com.example.taskhotelapp.domain.repositories.RoomBookingRepository

class GetRoomBookingUseCase(private val repository: RoomBookingRepository) {
    suspend fun execute(): Resource<RoomBooking> {
        return repository.getRoomBooking()
    }
}