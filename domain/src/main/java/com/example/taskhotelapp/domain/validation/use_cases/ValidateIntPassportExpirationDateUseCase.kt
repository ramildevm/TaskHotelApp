package com.example.taskhotelapp.domain.validation.use_cases

import android.util.Patterns
import com.example.taskhotelapp.domain.validation.ValidationResult

class ValidateIntPassportExpirationDateUseCase {
    fun execute(date: String): ValidationResult {
        if(date.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите срок действия загранпаспорта"
            )
        }
        if(date.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Неправильная дата"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}