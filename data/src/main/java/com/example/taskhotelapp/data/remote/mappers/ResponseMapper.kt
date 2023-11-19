package com.example.taskhotelapp.data.remote.mappers

import com.example.taskhotelapp.data.remote.models.AboutTheHotelResponse
import com.example.taskhotelapp.data.remote.models.HotelDetailsResponse
import com.example.taskhotelapp.data.remote.models.RoomBookingResponse
import com.example.taskhotelapp.data.remote.models.RoomResponse
import com.example.taskhotelapp.domain.models.AboutTheHotel
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.domain.models.Room
import com.example.taskhotelapp.domain.models.RoomBooking

fun HotelDetailsResponse.toModel(): HotelDetails {
    return HotelDetails(
        id = this.id,
        name = this.name,
        address = this.adress,
        minimalPrice = this.minimal_price,
        priceForInfo = this.price_for_it,
        rating = this.rating,
        ratingName = this.rating_name,
        photos = this.image_urls,
        about = this.about_the_hotel.toModel()
    )
}

fun AboutTheHotelResponse.toModel(): AboutTheHotel {
    return AboutTheHotel(
        description = this.description,
        peculiarities = this.peculiarities
    )
}

fun RoomResponse.toModel(): Room {
    return Room(
        id = this.id,
        name = this.name,
        price = this.price,
        priceForInfo = this.price_per,
        peculiarities = this.peculiarities,
        photos = this.image_urls
    )
}
fun RoomBookingResponse.toModel(): RoomBooking {
    return RoomBooking(
        id = this.id,
        hotelName = this.hotel_name,
        hotelAddress = this.hotel_adress,
        hotelRating = this.horating,
        ratingName = this.rating_name,
        departure = this.departure,
        arrivalCountry = this.arrival_country,
        tourDateStart = this.tour_date_start,
        tourDateStop = this.tour_date_stop,
        nightsNumber = this.number_of_nights,
        room = this.room,
        nutrition = this.nutrition,
        tourPrice = this.tour_price,
        fuelCharge = this.fuel_charge,
        serviceCharge = this.service_charge
    )
}