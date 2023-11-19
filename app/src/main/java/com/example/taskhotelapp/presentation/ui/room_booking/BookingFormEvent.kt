package com.example.taskhotelapp.presentation.ui.room_booking

import com.example.taskhotelapp.presentation.ui.room_booking.models.TouristFormState

sealed class BookingFormEvent {
    data class PhoneNumberChanged(val phoneNumber: String) : BookingFormEvent()
    data class EmailChanged(val email: String) : BookingFormEvent()
    data class Submit(val tourists: List<TouristFormState>) : BookingFormEvent()
}

