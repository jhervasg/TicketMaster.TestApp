package com.example.ticketmasterapp.domain

import com.example.ticketmasterapp.data.local.TicketsModel

interface TicketsDbRepository {
    suspend fun readTicketsDb(): List<TicketsModel>

    suspend fun addTicketsDb(ticketsModel : List<TicketsModel>?)

    suspend fun deleteTicketsDb()

    suspend fun getTicketsCount() : Boolean
}