package com.example.ticketmasterapp.data.remote

import com.example.ticketmasterapp.data.remote.response.TicketsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketApiService {

    @GET("/discovery/v2/events.json")
    suspend fun listTickets(
        @Query("apikey") apikey: String,
    ): Response<TicketsResponse>

}