package com.example.ticketmasterapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketmasterapp.data.local.TicketsModel
import com.example.ticketmasterapp.domain.TicketsRepository
import com.example.ticketmasterapp.util.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TicketsRepository
) : ViewModel() {

    private val _getTickets = MutableLiveData<List<TicketsModel>?>()
    val getTickets: MutableLiveData<List<TicketsModel>?> = _getTickets

    private val _getTicketsStatus = MutableLiveData<Boolean>()
    val getTicketsStatus: MutableLiveData<Boolean> = _getTicketsStatus

    fun callTickets() {
        try {
            viewModelScope.launch {
                val ticketsRoom = repository.getTickets(API_KEY) ?: emptyList<TicketsModel>()
                if (ticketsRoom.isEmpty()) {
                    _getTicketsStatus.value = true
                } else {
                    _getTickets.value = ticketsRoom
                }
            }
        } catch (e: Exception) {
            Log.d("ErrorJH: ", "" + e.message)
        }

    }
}