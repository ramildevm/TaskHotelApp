package com.example.taskhotelapp.di

import com.example.taskhotelapp.data.di.DataModule
import com.example.taskhotelapp.domain.di.DomainModule
import com.example.taskhotelapp.presentation.di.PresentationModule
import com.example.taskhotelapp.presentation.ui.hotel_details.HotelDetailsFragment
import com.example.taskhotelapp.presentation.ui.room_booking.RoomBookingFragment
import com.example.taskhotelapp.presentation.ui.rooms.RoomsFragment
import dagger.Component

@Component(modules = [PresentationModule::class,DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(fragment: RoomBookingFragment)
    fun inject(fragment: HotelDetailsFragment)
    fun inject(fragment: RoomsFragment)
}