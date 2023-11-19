package com.example.taskhotelapp.domain.use_cases

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.models.Room
import com.example.taskhotelapp.domain.repositories.HotelDetailsRepository
import com.example.taskhotelapp.domain.repositories.RoomsRepository

class GetRoomsUseCase(private val repository: RoomsRepository) {
    suspend fun execute(): Resource<List<Room>> {
        return repository.getRooms()
    }
}