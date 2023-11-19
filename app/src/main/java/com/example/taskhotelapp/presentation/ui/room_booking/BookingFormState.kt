package com.example.taskhotelapp.presentation.ui.room_booking

data class BookingFormState(
    val phoneNumber:String = "",
    val phoneNumberError:String?=null,
    val email:String = "",
    val emailError:String?=null
)
