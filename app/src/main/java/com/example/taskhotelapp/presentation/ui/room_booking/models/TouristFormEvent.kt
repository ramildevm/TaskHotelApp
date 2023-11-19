package com.example.taskhotelapp.presentation.ui.room_booking.models

import com.example.taskhotelapp.presentation.ui.room_booking.BookingFormEvent

sealed class TouristFormEvent {
    data class NameChanged(val name: String) : TouristFormEvent()
    data class SurnameChanged(val surname: String) : TouristFormEvent()
    data class BirthDateChanged(val birthDate: String) : TouristFormEvent()
    data class CitizenShipChanged(val citizenShip: String) : TouristFormEvent()
    data class IntPassportNumberChanged(val intPassportNumber: String) : TouristFormEvent()
    data class IntPassportExpirationDateChanged(val intPassportExpirationDate: String) : TouristFormEvent()
}