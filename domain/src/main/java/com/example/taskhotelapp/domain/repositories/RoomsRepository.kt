package com.example.taskhotelapp.domain.repositories

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.models.Room

interface RoomsRepository {
    suspend fun getRooms(): Resource<List<Room>>
}