package com.example.rickandmortyapp.ui.location

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationsFragment: Fragment(R.layout.fragment_all_locations) {

    companion object {
        fun newInstance() = LocationsFragment()
        const val TAG = "LocationsFragment"
    }
    private lateinit var adapter: LocationsAdapter
    private lateinit var onLocationClickedListener: OnLocationClickedListener

    private val viewModel: LocationsViewModel by lazy {
        ViewModelProvider(this).get(LocationsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onLocationClickedListener = context as OnLocationClickedListener
        adapter = LocationsAdapter(LocationComparator) {
            onLocationClickedListener.onLocationClicked(it)
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

    interface OnLocationClickedListener {
        fun onLocationClicked(id: Int)
    }
}