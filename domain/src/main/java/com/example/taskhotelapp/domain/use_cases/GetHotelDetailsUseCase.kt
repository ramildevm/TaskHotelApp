package com.example.taskhotelapp.domain.use_cases

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.repositories.HotelDetailsRepository

class GetHotelDetailsUseCase(private val repository: HotelDetailsRepository) {
    suspend fun execute(): Resource<HotelDetails> {
        return repository.getHotelDetails()
    }
}