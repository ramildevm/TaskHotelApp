package com.example.taskhotelapp.presentation.ui.room_booking.adapters

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskhotelapp.R
import com.example.taskhotelapp.domain.validation.use_cases.ValidateBirthDateUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateCitizenshipUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateIntPassportExpirationDateUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateIntPassportNumberUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateNameUseCase
import com.example.taskhotelapp.domain.validation.use_cases.ValidateSurnameUseCase
import com.example.taskhotelapp.presentation.UiUtil
import com.example.taskhotelapp.presentation.ui.room_booking.models.TouristFormEvent
import com.example.taskhotelapp.presentation.ui.room_booking.models.TouristFormState
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class TouristFormAdapter(
    private val context: Context,
    private val touristStateList: ArrayList<TouristFormState>,
    private val validateNameUseCase: ValidateNameUseCase = ValidateNameUseCase(),
    private val validateSurnameUseCase: ValidateSurnameUseCase = ValidateSurnameUseCase(),
    private val validateCitizenshipUseCase: ValidateCitizenshipUseCase = ValidateCitizenshipUseCase(),
    private val validateBirthDateUseCase: ValidateBirthDateUseCase = ValidateBirthDateUseCase(),
    private val validateIntPassportNumberUseCase: ValidateIntPassportNumberUseCase = ValidateIntPassportNumberUseCase(),
    private val validateIntPassportExpirationDateUseCase: ValidateIntPassportExpirationDateUseCase = ValidateIntPassportExpirationDateUseCase()
):RecyclerView.Adapter<TouristFormAdapter.TouristFormViewHolder>() {
    inner class TouristFormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView:TextView = itemView.findViewById(R.id.title_txt)
        private val nameEditText:TextInputEditText = itemView.findViewById(R.id.tourist_name_txt)
        private val surnameEditText:TextInputEditText = itemView.findViewById(R.id.tourist_surname_txt)
        private val birthDateEditText:TextInputEditText = itemView.findViewById(R.id.tourist_birth_date_txt)
        private val citizenshipEditText:TextInputEditText = itemView.findViewById(R.id.tourist_citizenship_txt)
        private val intPassportNumberEditText:TextInputEditText = itemView.findViewById(R.id.tourist_int_passport_number_txt)
        private val intPassportExpirationEditText:TextInputEditText = itemView.findViewById(R.id.tourist_int_passport_expiration_txt)
        private val nameViewLayout: TextInputLayout = itemView.findViewById(R.id.tourist_name_layout)
        private val surnameViewLayout:TextInputLayout = itemView.findViewById(R.id.tourist_surname_layout)
        private val birthDateViewLayout:TextInputLayout = itemView.findViewById(R.id.tourist_birth_date_layout)
        private val citizenshipViewLayout:TextInputLayout = itemView.findViewById(R.id.tourist_citizenship_layout)
        private val intPassportNumberViewLayout:TextInputLayout = itemView.findViewById(R.id.tourist_int_passport_number_layout)
        private val intPassportExpirationViewLayout:TextInputLayout = itemView.findViewById(R.id.tourist_int_passport_expiration_layout)
        fun bind(tourist: TouristFormState, position: Int) {
            val slots = UnderscoreDigitSlotsParser().parseSlots("__.__.____")
            val formatWatcherBirthDate: FormatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
            formatWatcherBirthDate.installOn(birthDateEditText)
            val formatWatcherExpDate: FormatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
            formatWatcherExpDate.installOn(intPassportExpirationEditText)
            nameEditText.setText(tourist.name)
            surnameEditText.setText(tourist.surname)
            birthDateEditText.setText(tourist.birthDate)
            citizenshipEditText.setText(tourist.citizenship)
            intPassportNumberEditText.setText(tourist.intPassportNumber)
            intPassportExpirationEditText.setText(tourist.intPassportExpirationDate)
            nameEditText.addTextChangedListener(createValidationTextWatcher{text->
                onEvent(TouristFormEvent.NameChanged(text), position)
            })
            surnameEditText.addTextChangedListener(createValidationTextWatcher{text->
                onEvent(TouristFormEvent.SurnameChanged(text), position)
            })
            birthDateEditText.addTextChangedListener(createValidationTextWatcher{text->
                onEvent(TouristFormEvent.BirthDateChanged(text), position)
            })
            citizenshipEditText.addTextChangedListener(createValidationTextWatcher{text->
                onEvent(TouristFormEvent.CitizenShipChanged(text), position)
            })
            intPassportNumberEditText.addTextChangedListener(createValidationTextWatcher{text->
                onEvent(TouristFormEvent.IntPassportNumberChanged(text), position)
            })
            intPassportExpirationEditText.addTextChangedListener(createValidationTextWatcher{text->
                onEvent(TouristFormEvent.IntPassportExpirationDateChanged(text), position)
            })
            nameViewLayout.isErrorEnabled = tourist.nameError!=null
            nameViewLayout.error = tourist.nameError

            surnameViewLayout.isErrorEnabled = tourist.surnameError!=null
            surnameViewLayout.error = tourist.surnameError

            birthDateViewLayout.isErrorEnabled = tourist.birthDateError!=null
            birthDateViewLayout.error = tourist.birthDateError

            citizenshipViewLayout.isErrorEnabled = tourist.citizenshipError!=null
            citizenshipViewLayout.error = tourist.citizenshipError

            intPassportNumberViewLayout.isErrorEnabled = tourist.intPassportNumberError!=null
            intPassportNumberViewLayout.error = tourist.intPassportNumberError

            intPassportExpirationViewLayout.isErrorEnabled = tourist.intPassportExpirationDateError!=null
            intPassportExpirationViewLayout.error = tourist.intPassportExpirationDateError

            titleView.text = context.getString(
                R.string.tourist_count_txt,
                UiUtil.getNumberText(context, position + 1),
                context.getString(R.string.tourist)
            )
        }
    }
    private fun onEvent(event: TouristFormEvent, position: Int){
        when(event){
            is TouristFormEvent.BirthDateChanged -> {
                val result = validateBirthDateUseCase.execute(event.birthDate)
                insertNewItem(
                    position = position,
                    newTourist = touristStateList[position].copy(
                    birthDate = event.birthDate,
                    birthDateError = result.errorMessage
                ))
            }
            is TouristFormEvent.CitizenShipChanged -> {
                val result = validateCitizenshipUseCase.execute(event.citizenShip)
                insertNewItem(
                    position = position,
                    newTourist = touristStateList[position].copy(
                    citizenship = event.citizenShip,
                    citizenshipError = result.errorMessage
                ))
            }
            is TouristFormEvent.NameChanged -> {
                val result = validateNameUseCase.execute(event.name)
                insertNewItem(
                    position = position,
                    newTourist = touristStateList[position].copy(
                        name = event.name,
                        nameError = result.errorMessage
                    ))
            }
            is TouristFormEvent.SurnameChanged -> {
                val result = validateSurnameUseCase.execute(event.surname)
                insertNewItem(
                    position = position,
                    newTourist = touristStateList[position].copy(
                        surname = event.surname,
                        surnameError = result.errorMessage
                    ))
            }
            is TouristFormEvent.IntPassportExpirationDateChanged -> {
                val result = validateIntPassportExpirationDateUseCase.execute(event.intPassportExpirationDate)
                insertNewItem(
                    position = position,
                    newTourist = touristStateList[position].copy(
                        intPassportExpirationDate = event.intPassportExpirationDate,
                        intPassportExpirationDateError = result.errorMessage
                    ))
            }
            is TouristFormEvent.IntPassportNumberChanged -> {
                val result = validateIntPassportNumberUseCase.execute(event.intPassportNumber)
                insertNewItem(
                    position = position,
                    newTourist = touristStateList[position].copy(
                        intPassportNumber = event.intPassportNumber,
                        intPassportNumberError = result.errorMessage
                    ))
            }
        }
    }
    private fun insertNewItem(position:Int, newTourist:TouristFormState){
        touristStateList[position] = newTourist
    }
    private fun createValidationTextWatcher(event: (String) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                event(text.toString())
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristFormViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tourist_form_layout, parent, false)
        return TouristFormViewHolder(view)
    }

    override fun getItemCount(): Int {
        return touristStateList.size
    }

    override fun onBindViewHolder(holder: TouristFormViewHolder, position: Int) {
        holder.bind(touristStateList[position], position)
    }

    fun add(touristForm: TouristFormState) {
        touristStateList.add(touristForm.apply { id = touristStateList.size})
        notifyItemInserted(touristStateList.size)
    }
    fun getValidatedItems(): ArrayList<TouristFormState> {
        return ArrayList(touristStateList.map {
            it.nameError = validateNameUseCase.execute(it.name).errorMessage
            it.surnameError = validateSurnameUseCase.execute(it.surname).errorMessage
            it.birthDateError = validateBirthDateUseCase.execute(it.birthDate).errorMessage
            it.citizenshipError = validateCitizenshipUseCase.execute(it.citizenship).errorMessage
            it.intPassportNumberError = validateIntPassportNumberUseCase.execute(it.intPassportNumber).errorMessage
            it.intPassportExpirationDateError = validateIntPassportExpirationDateUseCase.execute(it.intPassportExpirationDate).errorMessage
            it
        }.sortedBy { it.id })
    }

}