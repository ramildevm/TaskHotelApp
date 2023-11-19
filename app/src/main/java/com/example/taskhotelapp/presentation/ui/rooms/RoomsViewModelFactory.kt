package com.example.taskhotelapp.presentation.ui.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskhotelapp.domain.use_cases.GetRoomsUseCase

class RoomsViewModelFactory(val getRoomsUseCase: GetRoomsUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RoomsViewModel(
            getRoomsUseCase = getRoomsUseCase
        ) as T
    }
}
