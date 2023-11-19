package com.example.taskhotelapp.data.repositories

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.data.remote.api.HotelDetailsApi
import com.example.taskhotelapp.data.remote.mappers.toModel
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.repositories.HotelDetailsRepository

class HotelDetailsRepositoryImpl(
    private val api: HotelDetailsApi
):HotelDetailsRepository {
    override suspend fun getHotelDetails(): Resource<HotelDetails> {
        val response = api.getHotelDetails()
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success( resultResponse.toModel())
            }
        }
        return Resource.Error(response.message())
    }
}