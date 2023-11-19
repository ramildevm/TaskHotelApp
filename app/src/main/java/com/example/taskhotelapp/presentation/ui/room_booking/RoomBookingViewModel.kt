package com.example.taskhotelapp.presentation.ui.room_booking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.domain.models.RoomBooking
import com.example.taskhotelapp.domain.use_cases.GetRoomBookingUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateEmailUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidatePhoneNumberUseCase
import com.example.taskhotelapp.presentation.ui.room_booking.models.TouristFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RoomBookingViewModel(
    private val getRoomBookingUseCase: GetRoomBookingUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase = ValidateEmailUseCase(),
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase = ValidatePhoneNumberUseCase()
) : ViewModel() {
    val state:MutableLiveData<BookingFormState> = MutableLiveData<BookingFormState>()
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    init {
        state.value = BookingFormState()
    }
    val roomsList: MutableLiveData<Resource<RoomBooking>> = MutableLiveData()
    fun loadBookingInfo(){
        viewModelScope.launch {
            roomsList.postValue(Resource.Loading())
            val resource = getRoomBookingUseCase.execute()
            roomsList.postValue(resource)
        }
    }
    fun onEvent(event: BookingFormEvent){
        when(event){
            is BookingFormEvent.EmailChanged -> {
                val emailResult = validateEmailUseCase.execute(event.email)
                state.postValue(state.value?.copy(email=event.email, emailError = emailResult.errorMessage))
            }
            is BookingFormEvent.PhoneNumberChanged -> {
                val phoneResult = validatePhoneNumberUseCase.execute(event.phoneNumber)
                state.postValue(state.value?.copy(phoneNumber = event.phoneNumber, phoneNumberError = phoneResult.errorMessage))
            }
            is BookingFormEvent.Submit ->{
                submitData(event.tourists)
            }
        }
    }
    private fun submitData(tourists: List<TouristFormState>) {
        val phoneResult = validatePhoneNumberUseCase.execute(state.value?.phoneNumber?:"")
        val emailResult = validateEmailUseCase.execute(state.value?.email?:"")
        val hasError = listOf(
            phoneResult,
            emailResult
        ).any { !it.successful }
        val hasTouristError = tourists.any { tourist ->
            tourist.nameError != null ||
                    tourist.surnameError != null ||
                    tourist.birthDateError != null ||
                    tourist.citizenshipError != null ||
                    tourist.intPassportNumberError != null ||
                    tourist.intPassportExpirationDateError != null
        }
        if(hasError || hasTouristError) {
            state.postValue(state.value?.copy(
                phoneNumberError = phoneResult.errorMessage,
                emailError = emailResult.errorMessage
            ))
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Error)
            }
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    sealed class ValidationEvent {
        data object Success: ValidationEvent()
        data object Error: ValidationEvent()
    }
}