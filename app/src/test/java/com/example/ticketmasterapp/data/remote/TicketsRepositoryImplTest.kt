package com.example.ticketmasterapp.data.remote

import android.app.Application
import com.example.ticketmasterapp.domain.TicketsDbRepository
import com.example.ticketmasterapp.util.isNetworkAvailable
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TicketsRepositoryImplTest {

    private lateinit var repository: TicketsRepositoryImpl
    private lateinit var ticketApiService: TicketApiService
    private lateinit var dbRepository: TicketsDbRepository
    private lateinit var context: Application

    @Before
    fun setUp() {
        ticketApiService = mockk()
        dbRepository = mockk()
        context = mockk()
        repository = TicketsRepositoryImpl(ticketApiService, dbRepository, context)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getTickets when network is available and db is empty should fetch from API and save to DB`() {
        every { context.applicationContext } returns context
        every { isNetworkAvailable(context) } returns true
        coEvery { ticketApiService.listTickets(any()) } returns mockk {
            every { body() } returns mockk {
                every { embedded } returns mockk {
                    every { events } returns listOf(
                        mockk {
                            every { name } returns ""
                            every { type } returns ""
                            every { url } returns ""
                            every { images } returns listOf(mockk {
                                every { url } returns ""
                            })
                            every { dates } returns mockk {
                                every { start } returns mockk {
                                    every { localDate } returns ""
                                }
                            }
                            every { placeInfo } returns mockk {
                                every { placeInfo } returns listOf(mockk {
                                    every { placeName } returns ""
                                    every { state.name } returns ""
                                    every { state.stateCode } returns ""
                                })
                            }
                        }
                    )
                }
            }
        }
        coEvery { dbRepository.getTicketsCount() } returns true
        coEvery { dbRepository.deleteTicketsDb() } just Runs
        coEvery { dbRepository.addTicketsDb(any()) } just Runs

        val result = runBlocking { repository.getTickets("API_KEY") }

        assertEquals(1, result?.size)
        assertEquals("", result?.get(0)?.name)
        assertEquals("", result?.get(0)?.type)
        assertEquals("", result?.get(0)?.url)
        assertEquals("", result?.get(0)?.imageUrl)
        assertEquals("", result?.get(0)?.localDate)
        assertEquals("", result?.get(0)?.placeName)
        assertEquals("", result?.get(0)?.state)
        assertEquals("", result?.get(0)?.stateMin)
        coVerify { dbRepository.deleteTicketsDb() }
        coVerify { dbRepository.addTicketsDb(any()) }
    }
}
