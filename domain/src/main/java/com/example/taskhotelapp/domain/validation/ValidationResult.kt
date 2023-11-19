package com.example.taskhotelapp.domain.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
