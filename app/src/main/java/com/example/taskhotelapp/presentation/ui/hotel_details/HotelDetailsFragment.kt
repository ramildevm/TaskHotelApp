package com.example.taskhotelapp.presentation.ui.hotel_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.common.taskhotelapp.util.Resource
import com.example.common.taskhotelapp.util.toPriceFormat
import com.example.taskhotelapp.R
import com.example.taskhotelapp.app.App
import com.example.taskhotelapp.databinding.FragmentHotelDetailsBinding
import com.example.taskhotelapp.domain.models.HotelDetails
import com.example.taskhotelapp.presentation.adapters.ImagePagerAdapter
import com.example.taskhotelapp.presentation.adapters.PeculiaritiesAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class HotelDetailsFragment : Fragment() {
    private lateinit var binding: FragmentHotelDetailsBinding

    @javax.inject.Inject
    lateinit var vmFactory: HotelDetailsViewModelFactory
    private lateinit var viewModel: HotelDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireContext().applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory)[HotelDetailsViewModel::class.java]

        binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)
        viewModel.loadHotelDetails()
        viewModel.hotelDetails.observe(viewLifecycleOwner){
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

        binding.goRoomChooseBtn.setOnClickListener {
            val hotelName = binding.hotelNameTxt.text
            val bundle = bundleOf("h_name" to hotelName)
            Navigation.findNavController(binding.root).navigate(R.id.action_hotelDetailsFragment_to_roomsFragment, bundle)
        }
        return binding.root
    }

    private fun bindData(data: HotelDetails?) {
        binding.apply {
            data?.let {
                hotelNameTxt.text = data.name
                hotelAddressTxt.text = data.address
                minimalPriceTxt.text = getString(
                    R.string.from_price_txt_format,
                    data.minimalPrice.toString().toPriceFormat()
                )
                priceForTxt.text = data.priceForInfo
                ratingTxt.text = getString(R.string.rating_txt_format, data.rating, data.ratingName)
                descriptionTxt.text = data.about.description
                peculiaritiesRecyclerView.adapter = PeculiaritiesAdapter(requireContext(),data.about.peculiarities)

                val layoutManager = FlexboxLayoutManager(requireContext())
                layoutManager.flexDirection = FlexDirection.ROW
                layoutManager.justifyContent = JustifyContent.FLEX_START
                binding.peculiaritiesRecyclerView.layoutManager = layoutManager

                binding.imagePager.adapter = ImagePagerAdapter(requireContext(),data.photos)
                binding.indicator.setViewPager(binding.imagePager)
            }

        }
    }
}