package com.example.taskhotelapp.presentation.ui.order_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.taskhotelapp.R
import com.example.taskhotelapp.databinding.FragmentOrderResultBinding
import kotlin.random.Random

class OrderResultFragment : Fragment() {

    private lateinit var binding:FragmentOrderResultBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderResultBinding.inflate(inflater, container, false)
        binding.titleTxt.text = getString(R.string.order_result_title_txt)
        binding.orderStatus.text = getString(R.string.order_accepted)
        val randomNum = Random.nextInt(100000, 999999)
        binding.resultText.text = getString(
            R.string.order_result_txt,
            randomNum
        )
        binding.topGoBackBtn.setOnClickListener{
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_orderResultFragment_to_roomBookingFragment)
        }
        binding.goHotelDetailsBtn.setOnClickListener{
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_orderResultFragment_to_hotelDetailsFragment)
        }
        return binding.root
    }
}