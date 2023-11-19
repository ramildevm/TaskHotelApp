package com.example.taskhotelapp.presentation.ui.room_booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskhotelapp.domain.use_cases.GetRoomBookingUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateEmailUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidatePhoneNumberUseCase

class RoomBookingViewModelFactory(
    val getRoomBookingUseCase: GetRoomBookingUseCase,
    val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    val validateEmailUseCase: ValidateEmailUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RoomBookingViewModel(
            getRoomBookingUseCase=getRoomBookingUseCase,
            validateEmailUseCase=validateEmailUseCase,
            validatePhoneNumberUseCase =validatePhoneNumberUseCase
        ) as T
    }
}