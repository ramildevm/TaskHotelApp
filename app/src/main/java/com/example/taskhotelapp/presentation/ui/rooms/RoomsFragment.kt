package com.example.taskhotelapp.presentation.ui.rooms

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.common.taskhotelapp.util.Resource
import com.example.taskhotelapp.R
import com.example.taskhotelapp.app.App
import com.example.taskhotelapp.databinding.FragmentRoomsBinding
import com.example.taskhotelapp.domain.models.Room
import com.example.taskhotelapp.presentation.ui.rooms.adapters.RoomAdapter

class RoomsFragment : Fragment() {
    @javax.inject.Inject
    lateinit var vmFactory: RoomsViewModelFactory
    private lateinit var viewModel: RoomsViewModel
    private lateinit var binding: FragmentRoomsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomsBinding.inflate(inflater, container, false)

        (requireContext().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(RoomsViewModel::class.java)
        viewModel.loadRoomsList()
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
        binding.titleHotelNameTxt.text = arguments?.getString("h_name")
        binding.topGoBackBtn.setOnClickListener{
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_roomsFragment_to_hotelDetailsFragment)
        }
        return binding.root
    }

    private fun bindData(data: List<Room>?) {
        data?.let {
            val adapter =  RoomAdapter(requireContext(),data)
            adapter.setOnItemClickListener {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_roomsFragment_to_roomBookingFragment)
            }
            binding.roomsContainerRecyclerView.adapter = adapter
        }
    }

}