package com.example.ticketmasterapp.data.local

import com.example.ticketmasterapp.data.local.database.dao.TicketsDao
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TicketsDbRepositoryImplTest {

    private lateinit var repository: TicketsDbRepositoryImpl
    private lateinit var ticketsDao: TicketsDao

    @Before
    fun setUp() {
        ticketsDao = mockk()
        repository = TicketsDbRepositoryImpl(ticketsDao)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `readTicketsDb should return list of tickets`() {
        val ticketsList = listOf(
            TicketsModel(0, "", "", "", "", "", "", "", ""),
            TicketsModel(0, "", "", "", "", "", "", "", ""),
        )
        coEvery { ticketsDao.readAllTicketsResults() } returns ticketsList

        val result = runBlocking { repository.readTicketsDb() }

        assertEquals(ticketsList, result)
        coVerify(exactly = 1) { ticketsDao.readAllTicketsResults() }
    }

    @Test
    fun `addTicketsDb should call dao's add method`() {
        val ticketsList = listOf(
            TicketsModel(0, "", "", "", "", "", "", "", ""),
            TicketsModel(0, "", "", "", "", "", "", "", ""),
        )

        runBlocking { repository.addTicketsDb(ticketsList) }

        coVerify(exactly = 1) { ticketsDao.addTicketsResults(ticketsList) }
    }

    @Test
    fun `addTicketsDb should not call dao's add method if ticketsModel is null`() {
        runBlocking { repository.addTicketsDb(null) }

        verify { ticketsDao wasNot Called }
    }

    @Test
    fun `getTicketsCount should return true if dao's row count is 0`() {
        coEvery { ticketsDao.getTicketsRowCount() } returns 0

        val result = runBlocking { repository.getTicketsCount() }

        assertEquals(true, result)
        coVerify(exactly = 1) { ticketsDao.getTicketsRowCount() }
    }

    @Test
    fun `getTicketsCount should return false if dao's row count is not 0`() {
        coEvery { ticketsDao.getTicketsRowCount() } returns 1

        val result = runBlocking { repository.getTicketsCount() }

        assertEquals(false, result)
        coVerify(exactly = 1) { ticketsDao.getTicketsRowCount() }
    }
}
