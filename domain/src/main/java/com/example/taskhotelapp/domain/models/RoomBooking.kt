package com.example.taskhotelapp.domain.models

data class RoomBooking(
    val id: Int,
    val hotelName: String,
    val hotelAddress: String,
    val hotelRating: Int,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateStop: String,
    val nightsNumber: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: Int,
    val fuelCharge: Int,
    val serviceCharge: Int
)