package com.example.ticketmasterapp.domain

import com.example.ticketmasterapp.data.local.TicketsModel

interface TicketsRepository {
    suspend fun getTickets(apiKey: String): List<TicketsModel>?
}