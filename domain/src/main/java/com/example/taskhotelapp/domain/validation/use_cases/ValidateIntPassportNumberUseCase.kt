package com.example.taskhotelapp.domain.validation.use_cases

import android.util.Patterns
import com.example.taskhotelapp.domain.validation.ValidationResult

class ValidateIntPassportNumberUseCase {
    fun execute(number: String): ValidationResult {
        if(number.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите номер загранпаспорта"
            )
        }
        if(number.length < 9) {
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