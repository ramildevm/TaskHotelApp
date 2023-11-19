package com.example.taskhotelapp.presentation.ui.rooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.Room
import com.example.taskhotelapp.domain.use_cases.GetRoomsUseCase
import kotlinx.coroutines.launch

class RoomsViewModel(val getRoomsUseCase: GetRoomsUseCase) : ViewModel() {
    val roomsList: MutableLiveData<Resource<List<Room>>> = MutableLiveData()
    fun loadRoomsList(){
        viewModelScope.launch {
            roomsList.postValue(Resource.Loading())
            val resource = getRoomsUseCase.execute()
            roomsList.postValue(resource)
        }
    }
}