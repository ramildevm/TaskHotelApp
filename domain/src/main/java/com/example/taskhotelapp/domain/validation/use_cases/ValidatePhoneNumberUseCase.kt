package com.example.taskhotelapp.domain.validation.use_cases

import com.example.taskhotelapp.domain.validation.ValidationResult

class ValidatePhoneNumberUseCase {
    fun execute(phoneNumber: String): ValidationResult {
        if(phoneNumber.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите номер телефона"
            )
        }
        if(phoneNumber.length<18) {
            return ValidationResult(
                successful = false,
                errorMessage = "Неправильный номер"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}