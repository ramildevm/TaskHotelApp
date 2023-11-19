package com.example.taskhotelapp.domain.models

data class Room(
    val id: Int,
    val name: String,
    val price: Int,
    val priceForInfo: String,
    val peculiarities: List<String>,
    val photos: List<String>
)