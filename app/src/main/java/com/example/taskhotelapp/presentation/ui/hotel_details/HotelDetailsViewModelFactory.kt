package com.example.taskhotelapp.presentation.ui.hotel_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskhotelapp.domain.use_cases.GetHotelDetailsUseCase

class HotelDetailsViewModelFactory(val getHotelDetailsUseCase: GetHotelDetailsUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HotelDetailsViewModel(
            getHotelDetailsUseCase = getHotelDetailsUseCase
        ) as T
    }
}
