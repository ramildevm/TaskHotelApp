package com.example.taskhotelapp.domain.models

data class HotelDetails(
    val id: Int,
    val name: String,
    val address: String,
    val minimalPrice: Int,
    val priceForInfo: String,
    val rating: Int,
    val ratingName: String,
    val photos: List<String>,
    val about: AboutTheHotel
)