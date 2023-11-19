package com.example.taskhotelapp.domain.validation.use_cases

import android.util.Patterns
import com.example.taskhotelapp.domain.validation.ValidationResult

class ValidateBirthDateUseCase {
    fun execute(birthDate: String): ValidationResult {
        if(birthDate.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите дату рождения"
            )
        }
        if(birthDate.length < 10) {
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