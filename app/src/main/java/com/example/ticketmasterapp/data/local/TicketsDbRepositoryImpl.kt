package com.example.ticketmasterapp.data.local

import com.example.ticketmasterapp.data.local.database.dao.TicketsDao
import com.example.ticketmasterapp.domain.TicketsDbRepository
import javax.inject.Inject

class TicketsDbRepositoryImpl @Inject constructor(
    private val ticketDbDao: TicketsDao
) : TicketsDbRepository {
    override suspend fun readTicketsDb(): List<TicketsModel> {
        return ticketDbDao.readAllTicketsResults()
    }

    override suspend fun addTicketsDb(ticketsModel: List<TicketsModel>?) {
        if (ticketsModel != null) {
            ticketDbDao.addTicketsResults(ticketsModel)
        }
    }

    override suspend fun deleteTicketsDb() {
        ticketDbDao.deleteAllTicketsResults()
    }

    override suspend fun getTicketsCount(): Boolean {
        return ticketDbDao.getTicketsRowCount() == 0
    }
}