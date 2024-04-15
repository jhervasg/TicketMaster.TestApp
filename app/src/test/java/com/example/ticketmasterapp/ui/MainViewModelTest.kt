package com.example.ticketmasterapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ticketmasterapp.data.local.TicketsModel
import com.example.ticketmasterapp.domain.TicketsRepository
import com.example.ticketmasterapp.util.API_KEY
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `callTickets success`() {
        val repository = mockk<TicketsRepository>()

        val tickets = listOf(TicketsModel(0, "", "", "", "", "", "", "", ""))
        coEvery { repository.getTickets(API_KEY) } returns tickets

        val viewModel = MainViewModel(repository)

        viewModel.callTickets()

        assertNotNull(viewModel.getTickets.value)
        assertEquals(tickets, viewModel.getTickets.value)
    }

    @Test
    fun `callTickets empty response`() {
        val repository = mockk<TicketsRepository>()

        coEvery { repository.getTickets(API_KEY) } returns emptyList()

        val viewModel = MainViewModel(repository)

        viewModel.callTickets()

        assertEquals(true, viewModel.getTicketsStatus.value)
    }

    @Test
    fun `callTickets error`() {
        val repository = mockk<TicketsRepository>()

        coEvery { repository.getTickets(API_KEY) } throws Exception("Mock exception")

        val viewModel = MainViewModel(repository)

        viewModel.callTickets()

        assertEquals(null, viewModel.getTickets.value)
        assertEquals(null, viewModel.getTicketsStatus.value)
    }
}
