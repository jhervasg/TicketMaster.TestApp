package com.example.ticketmasterapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketmasterapp.R
import com.example.ticketmasterapp.data.local.TicketsModel
import com.example.ticketmasterapp.util.convertDateFormat

class TicketsAdapter : RecyclerView.Adapter<TicketsAdapter.MyViewHolder>() {
    private var ticketList = emptyList<TicketsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ticket_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ticketData = ticketList[position]
        Glide.with(holder.imagetk.context)
            .load(ticketData.imageUrl)
            .into(holder.imagetk)
        holder.ticketTitle.text = ticketData.name
        holder.ticketDate.text = convertDateFormat(ticketData.localDate)
        holder.ticketPlace.text = ticketData.placeName
        holder.ticketLocation.text = ticketData.state + ", " + ticketData.stateMin
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTicketsModel(ticketsModel: List<TicketsModel>?) {
        if (ticketsModel != null) {
            this.ticketList = ticketsModel
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(ticketsModel: List<TicketsModel>?) {
        if (ticketsModel != null) {
            this.ticketList = ticketsModel
        }
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagetk: ImageView = itemView.findViewById(R.id.image_tk)
        val ticketTitle: TextView = itemView.findViewById(R.id.title_tk)
        var ticketDate: TextView = itemView.findViewById(R.id.date_tk)
        var ticketPlace: TextView = itemView.findViewById(R.id.place_tk)
        var ticketLocation: TextView = itemView.findViewById(R.id.location_tk)

    }
}