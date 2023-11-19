package com.example.taskhotelapp.domain.repositories

import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.HotelDetails

interface HotelDetailsRepository {
    suspend fun getHotelDetails(): Resource<HotelDetails>
}