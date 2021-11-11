package com.example.rickandmortyapp.ui.location

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.adapters.LocationComparator
import com.example.rickandmortyapp.ui.adapters.LocationsAdapter
import com.example.rickandmortyapp.ui.viewmodels.LocationsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationsFragment: Fragment(R.layout.fragment_all_locations) {

    private lateinit var adapter: LocationsAdapter

    private val viewModel: LocationsViewModel by lazy {
        ViewModelProvider(this).get(LocationsViewModel::class.java)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = LocationsAdapter(LocationComparator) { locationId ->
            val action = LocationsFragmentDirections
                .actionLocationsFragmentToLocationInfoFragment(locationId)
            findNavController().navigate(action)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.allLocationsRecyclerView)
        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.locationsFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}