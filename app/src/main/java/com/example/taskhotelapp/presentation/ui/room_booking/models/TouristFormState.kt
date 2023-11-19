package com.example.taskhotelapp.presentation.ui.room_booking.models

data class TouristFormState(
    var id:Int = 1,
    var name:String = "",
    var nameError:String? = null,
    var surname:String= "",
    var surnameError:String? = null,
    var birthDate:String= "",
    var birthDateError:String?=null,
    var citizenship:String= "",
    var citizenshipError:String?=null,
    var intPassportNumber:String= "",
    var intPassportNumberError:String?=null,
    var intPassportExpirationDate:String = "",
    var intPassportExpirationDateError:String?=null
)
