package com.example.taskhotelapp.presentation.ui.rooms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.common.taskhotelapp.util.toPriceFormat
import com.example.taskhotelapp.R
import com.example.taskhotelapp.domain.models.Room
import com.example.taskhotelapp.presentation.adapters.ImagePagerAdapter
import com.example.taskhotelapp.presentation.adapters.PeculiaritiesAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.button.MaterialButton
import me.relex.circleindicator.CircleIndicator3

class RoomAdapter(private val context: Context, private val rooms: List<Room>):RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    private var onItemClickListener: ((Room) -> Unit)? = null
    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePager: ViewPager2 = itemView.findViewById(R.id.image_pager)
        private val circleIndicator: CircleIndicator3 = itemView.findViewById(R.id.indicator)
        private val roomName: TextView = itemView.findViewById(R.id.room_name_txt)
        private val peculiaritiesRecyclerView: RecyclerView = itemView.findViewById(R.id.peculiarities_recycler_view)
        private val priceTxt: TextView = itemView.findViewById(R.id.price_txt)
        private val pricePerTxt: TextView = itemView.findViewById(R.id.price_per_txt)
        private val goBookingBtn: MaterialButton = itemView.findViewById(R.id.go_booking_screen_btn)
        fun bind(room: Room) {
            imagePager.adapter = ImagePagerAdapter(context, room.photos)
            circleIndicator.setViewPager(imagePager)
            roomName.text = room.name
            priceTxt.text = context.getString(
                R.string.price_txt_format,
                room.price.toString().toPriceFormat()
            )
            pricePerTxt.text = room.priceForInfo
            peculiaritiesRecyclerView.adapter = PeculiaritiesAdapter(context,room.peculiarities)
            val layoutManager = FlexboxLayoutManager(context)
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            peculiaritiesRecyclerView.layoutManager = layoutManager
            goBookingBtn.setOnClickListener{
                onItemClickListener?.let {
                    it(room)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(rooms[position])
    }

    fun setOnItemClickListener(listener: (Room) -> Unit) {
        onItemClickListener = listener
    }
    override fun getItemCount(): Int {
        return rooms.size
    }
}