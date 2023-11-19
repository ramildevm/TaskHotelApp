package com.example.taskhotelapp.data.di

import com.example.taskhotelapp.data.remote.Constants
import com.example.taskhotelapp.data.remote.api.HotelDetailsApi
import com.example.taskhotelapp.data.remote.api.RoomBookingApi
import com.example.taskhotelapp.data.remote.api.RoomsApi
import com.example.taskhotelapp.data.repositories.HotelDetailsRepositoryImpl
import com.example.taskhotelapp.data.repositories.RoomBookingRepositoryImpl
import com.example.taskhotelapp.data.repositories.RoomsRepositoryImpl
import com.example.taskhotelapp.domain.repositories.HotelDetailsRepository
import com.example.taskhotelapp.domain.repositories.RoomBookingRepository
import com.example.taskhotelapp.domain.repositories.RoomsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
class DataModule {
    @Provides
    fun provideHotelDetailsApi():HotelDetailsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    fun provideRoomsApi():RoomsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
    @Provides
    fun provideRoomBookingApi():RoomBookingApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
    @Provides
    fun provideHotelDetailsRepository(api: HotelDetailsApi):HotelDetailsRepository{
        return HotelDetailsRepositoryImpl(api)
    }
    @Provides
    fun provideRoomsRepository(api: RoomsApi):RoomsRepository{
        return RoomsRepositoryImpl(api)
    }
    @Provides
    fun provideRoomBookingRepository(api: RoomBookingApi):RoomBookingRepository{
        return RoomBookingRepositoryImpl(api)
    }
}