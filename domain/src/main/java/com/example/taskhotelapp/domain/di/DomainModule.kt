package com.example.taskhotelapp.domain.di

import com.example.taskhotelapp.domain.repositories.HotelDetailsRepository
import com.example.taskhotelapp.domain.repositories.RoomBookingRepository
import com.example.taskhotelapp.domain.repositories.RoomsRepository
import com.example.taskhotelapp.domain.use_cases.GetHotelDetailsUseCase
import com.example.taskhotelapp.domain.use_cases.GetRoomBookingUseCase
import com.example.taskhotelapp.domain.use_cases.GetRoomsUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateEmailUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidatePhoneNumberUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetHotelDetailsUseCase(repository: HotelDetailsRepository):GetHotelDetailsUseCase{
        return GetHotelDetailsUseCase(repository)
    }
    @Provides
    fun provideGetRoomsUseCase(repository: RoomsRepository):GetRoomsUseCase{
        return GetRoomsUseCase(repository)
    }
    @Provides
    fun provideGetRoomBookingUseCase(repository: RoomBookingRepository):GetRoomBookingUseCase{
        return GetRoomBookingUseCase(repository)
    }
    @Provides
    fun provideValidateEmailUseCase():ValidateEmailUseCase{
        return ValidateEmailUseCase()
    }
    @Provides
    fun provideValidatePhoneNumberUseCase():ValidatePhoneNumberUseCase{
        return ValidatePhoneNumberUseCase()
    }
}