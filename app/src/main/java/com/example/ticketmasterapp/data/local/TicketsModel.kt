package com.example.ticketmasterapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tickets_table")
data class TicketsModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val type: String,
    val url: String,
    val imageUrl: String,
    val localDate: String,
    val placeName: String,
    val state: String,
    val stateMin: String,
) : Parcelable