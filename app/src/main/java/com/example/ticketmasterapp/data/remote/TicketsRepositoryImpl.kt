package com.example.ticketmasterapp.data.remote

import android.app.Application
import com.example.ticketmasterapp.data.local.TicketsModel
import com.example.ticketmasterapp.domain.TicketsDbRepository
import com.example.ticketmasterapp.domain.TicketsRepository
import com.example.ticketmasterapp.util.isNetworkAvailable
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val ticketApiService: TicketApiService,
    private val dbRepository: TicketsDbRepository,
    val context: Application,
) : TicketsRepository {

    private var emptyTicketList: List<TicketsModel>? = emptyList()

    override suspend fun getTickets(apiKey: String): List<TicketsModel>? {

        when (dbRepository.getTicketsCount()) {
            true -> {
                if (isNetworkAvailable(context)) {
                    emptyTicketList =
                        ticketApiService.listTickets(apiKey).body().let { ticketResponse ->
                            ticketResponse?.embedded?.events.let { responseTk ->
                                responseTk?.map {
                                    TicketsModel(
                                        name = it.name,
                                        type = it.type,
                                        url = it.url,
                                        imageUrl = it.images[0].url,
                                        localDate = it.dates.start.localDate,
                                        placeName = it.placeInfo.placeInfo[0].placeName,
                                        state = it.placeInfo.placeInfo[0].state.name,
                                        stateMin = it.placeInfo.placeInfo[0].state.stateCode
                                    )
                                }
                            }
                        }
                    dbRepository.deleteTicketsDb()
                    dbRepository.addTicketsDb(emptyTicketList)
                }
            }

            false -> {
                emptyTicketList = dbRepository.readTicketsDb()
            }
        }
        return emptyTicketList
    }

}