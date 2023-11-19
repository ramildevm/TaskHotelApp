package com.example.taskhotelapp.presentation.di

import com.example.taskhotelapp.domain.use_cases.GetHotelDetailsUseCase
import com.example.taskhotelapp.domain.use_cases.GetRoomBookingUseCase
import com.example.taskhotelapp.domain.use_cases.GetRoomsUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateEmailUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidatePhoneNumberUseCase
import com.example.taskhotelapp.presentation.ui.hotel_details.HotelDetailsViewModelFactory
import com.example.taskhotelapp.presentation.ui.room_booking.RoomBookingViewModelFactory
import com.example.taskhotelapp.presentation.ui.rooms.RoomsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideRoomBookingViewModelFactory(
        getRoomBookingUseCase: GetRoomBookingUseCase,
        validateEmailUseCase: ValidateEmailUseCase,
        validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
    ): RoomBookingViewModelFactory {
        return RoomBookingViewModelFactory(
            getRoomBookingUseCase = getRoomBookingUseCase,
            validateEmailUseCase = validateEmailUseCase,
            validatePhoneNumberUseCase = validatePhoneNumberUseCase
        )
    }
    @Provides
    fun provideHotelDetailsViewModelFactory(
        getHotelDetailsUseCase: GetHotelDetailsUseCase
    ): HotelDetailsViewModelFactory {
        return HotelDetailsViewModelFactory(
            getHotelDetailsUseCase = getHotelDetailsUseCase
        )
    }
    @Provides
    fun provideRoomsViewModelFactory(
        getRoomsUseCase: GetRoomsUseCase
    ): RoomsViewModelFactory {
        return RoomsViewModelFactory(
            getRoomsUseCase = getRoomsUseCase
        )
    }
}