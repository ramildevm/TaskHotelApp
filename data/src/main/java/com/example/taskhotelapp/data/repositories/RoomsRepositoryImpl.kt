package com.example.taskhotelapp.data.repositories

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.data.remote.api.RoomsApi
import com.example.taskhotelapp.data.remote.mappers.toModel
import com.example.taskhotelapp.domain.models.Room
import com.example.taskhotelapp.domain.repositories.RoomsRepository

class RoomsRepositoryImpl(
    private val api: RoomsApi
):RoomsRepository {
    override suspend fun getRooms(): Resource<List<Room>> {
        val response = api.getRooms()
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success( resultResponse.rooms.map { it.toModel() })
            }
        }
        return Resource.Error(response.message())
    }
}