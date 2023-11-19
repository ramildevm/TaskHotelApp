package com.example.taskhotelapp.presentation.ui.hotel_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.use_cases.GetHotelDetailsUseCase
import kotlinx.coroutines.launch

class HotelDetailsViewModel(
    private val getHotelDetailsUseCase: GetHotelDetailsUseCase
) : ViewModel() {
    val hotelDetails: MutableLiveData<Resource<HotelDetails>> = MutableLiveData()
    fun loadHotelDetails(){
        viewModelScope.launch {
            hotelDetails.postValue(Resource.Loading())
            val resource = getHotelDetailsUseCase.execute()
            hotelDetails.postValue(resource)
        }
    }
}