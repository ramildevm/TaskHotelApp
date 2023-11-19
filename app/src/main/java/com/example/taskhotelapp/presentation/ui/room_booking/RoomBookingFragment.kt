package com.example.taskhotelapp.presentation.ui.room_booking

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.common.taskhotelapp.util.Resource
import com.example.common.taskhotelapp.util.toPriceFormat
import com.example.taskhotelapp.R
import com.example.taskhotelapp.app.App
import com.example.taskhotelapp.databinding.FragmentRoomBookingBinding
import com.example.taskhotelapp.domain.models.RoomBooking
import com.example.taskhotelapp.presentation.ui.room_booking.adapters.TouristFormAdapter
import com.example.taskhotelapp.presentation.ui.room_booking.models.TouristFormState
import kotlinx.coroutines.launch
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class RoomBookingFragment : Fragment() {
    @javax.inject.Inject
    lateinit var vmFactory: RoomBookingViewModelFactory
    private lateinit var viewModel: RoomBookingViewModel
    private lateinit var binding: FragmentRoomBookingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBookingBinding.inflate(inflater, container, false)
        (requireContext().applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory)[RoomBookingViewModel::class.java]

        val adapter = TouristFormAdapter(requireActivity(), arrayListOf())
        adapter.add(TouristFormState())
        binding.touristsContainer.adapter = adapter
        setTextWatchers()
        setFocusListeners()
        setOnClickListeners(adapter)

        viewModel.loadBookingInfo()
        viewModel.roomsList.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.messageTxt.text = it.message
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.messageTxt.text = null
                }
                is Resource.Success -> {
                    binding.infoLayout.visibility = View.GONE
                    bindData(it.data)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.validationEvents.collect { event ->
                when (event) {
                    RoomBookingViewModel.ValidationEvent.Success -> {
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_roomBookingFragment_to_orderResultFragment)
                    }
                    RoomBookingViewModel.ValidationEvent.Error -> {
                        //adapter.updateDataSet(adapter.getValidatedItems())
                        resetAdapterState()
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.not_all_fields_correct_warning),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        viewModel.state.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.phoneNumberError != null) {
                    binding.textNumberField.isErrorEnabled = true
                    binding.textNumberField.error = it.phoneNumberError
                } else
                    binding.textNumberField.isErrorEnabled = false

                if (it.emailError != null) {
                    binding.textEmailField.isErrorEnabled = true
                    binding.textEmailField.error = it.emailError
                } else
                    binding.textEmailField.isErrorEnabled = false
            }
        }
        return binding.root
    }
    private fun resetAdapterState() {
        val myAdapter = binding.touristsContainer.adapter
        binding.touristsContainer.adapter = myAdapter
    }
    private fun bindData(data: RoomBooking?) {
        binding.apply{
            data?.let{
                ratingTxt.text = getString(R.string.rating_txt_format, data.hotelRating, data.ratingName)
                hotelNameTxt.text = data.hotelName
                hotelAddressTxt.text = data.hotelAddress
                arrivalCountryTxt.text = data.arrivalCountry
                departureTxt.text = data.departure
                datesTxt.text = getString(
                    R.string.date_interval,
                    data.tourDateStart,
                    data.tourDateStop
                )
                nightsNumTxt.text = getString(
                    R.string.number_of_nights_txt,
                    data.nightsNumber
                )
                hotelNameInnerTxt.text = data.hotelName
                roomTxt.text = data.room
                nutritionTxt.text = data.nutrition
                tourPriceTxt.text = getString(
                    R.string.price_txt_format,
                    data.tourPrice.toString().toPriceFormat()
                )
                fuelPriceTxt.text = getString(
                    R.string.price_txt_format,
                    data.fuelCharge.toString().toPriceFormat()
                )
                servicePriceTxt.text = getString(
                    R.string.price_txt_format,
                    data.serviceCharge.toString().toPriceFormat()
                )
                val totalPrice = (data.tourPrice + data.fuelCharge + data.serviceCharge).toString().toPriceFormat()
                totalPriceTxt.text = getString(
                    R.string.price_txt_format, totalPrice
                )
                goRoomsBtn.text = getString(
                    R.string.payment_btn_txt,
                    totalPrice
                )
            }
        }
    }

    private fun setOnClickListeners(adapter: TouristFormAdapter) {
        binding.addTouristBtn.setOnClickListener {
            adapter.add(TouristFormState())
        }
        binding.goRoomsBtn.setOnClickListener {
            viewModel.onEvent(BookingFormEvent.Submit(adapter.getValidatedItems()))
        }
        binding.topGoBackBtn.setOnClickListener{
            val hotelName = binding.hotelNameTxt.text
            val bundle = bundleOf("h_name" to hotelName)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_roomBookingFragment_to_roomsFragment, bundle)
        }
    }

    private fun setFocusListeners() {
        binding.textNumberFieldInput.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                viewModel.onEvent(BookingFormEvent.PhoneNumberChanged(binding.textNumberFieldInput.text.toString()))
            }
        }
        binding.textEmailFieldInput.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                viewModel.onEvent(BookingFormEvent.EmailChanged(binding.textEmailFieldInput.text.toString()))
            }
        }
    }

    private fun setTextWatchers() {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        val watcher: FormatWatcher = MaskFormatWatcher(mask)
        watcher.installOn(binding.textNumberFieldInput)
        binding.textNumberFieldInput.addTextChangedListener(
            createValidationTextWatcher { text ->
                viewModel.onEvent(BookingFormEvent.PhoneNumberChanged(text))
            }
        )
        binding.textEmailFieldInput.addTextChangedListener(
            createValidationTextWatcher { text->
                viewModel.onEvent(BookingFormEvent.EmailChanged(text))
            }
        )
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
}