package com.example.taskhotelapp.domain.validation.use_cases

import android.util.Patterns
import com.example.taskhotelapp.domain.validation.ValidationResult

class ValidateSurnameUseCase {
    fun execute(surname: String): ValidationResult {
        if(surname.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите фамилию"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}