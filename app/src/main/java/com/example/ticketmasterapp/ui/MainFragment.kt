package com.example.ticketmasterapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ticketmasterapp.R
import com.example.ticketmasterapp.data.local.TicketsModel
import com.example.ticketmasterapp.data.remote.response.TicketsResponse
import com.example.ticketmasterapp.databinding.FragmentMainBinding
import com.example.ticketmasterapp.ui.adapter.TicketsAdapter
import com.example.ticketmasterapp.util.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var ticketData: List<TicketsModel>? = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ticketList.layoutManager = GridLayoutManager(context, 1)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            ticketList.adapter = TicketsAdapter()
            ticketList.itemAnimator = null
            search.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    val filteredList = ticketData?.filter { item ->
                        item.name.contains(p0.toString(), ignoreCase = true)
                    }
                    (ticketList.adapter as TicketsAdapter).filterList(filteredList)
                }

            })
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.callTickets()
            viewModel.getTickets.observe(viewLifecycleOwner) {
                ticketData = it
                with(binding) {
                    (ticketList.adapter as TicketsAdapter).setTicketsModel(it)
                }
            }
            viewModel.getTicketsStatus.observe(viewLifecycleOwner) {
                Toast.makeText(context, "No network avalilable", Toast.LENGTH_LONG).show()
            }
        }
    }
}