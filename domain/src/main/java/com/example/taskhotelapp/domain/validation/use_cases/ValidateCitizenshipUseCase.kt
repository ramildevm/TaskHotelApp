package com.example.taskhotelapp.domain.validation.use_cases

import android.util.Patterns
import com.example.taskhotelapp.domain.validation.ValidationResult

class ValidateCitizenshipUseCase {
    fun execute(citizenship: String): ValidationResult {
        if(citizenship.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Введите данные гражданства"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}