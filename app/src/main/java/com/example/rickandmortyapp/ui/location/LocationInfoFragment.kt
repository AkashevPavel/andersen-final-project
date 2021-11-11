package com.example.rickandmortyapp.ui.location

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.adapters.RelatedCharactersAdapter
import com.example.rickandmortyapp.ui.viewmodels.LocationInfoViewModel

class LocationInfoFragment : Fragment(R.layout.fragment_location_info) {

    private val viewModel: LocationInfoViewModel by lazy {
        ViewModelProvider(this).get(LocationInfoViewModel::class.java)
    }
    private lateinit var adapter: RelatedCharactersAdapter
    private lateinit var recyclerView: RecyclerView
    private val args by navArgs<LocationInfoFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = RelatedCharactersAdapter { characterId ->
            val action = LocationInfoFragmentDirections
                .actionLocationInfoFragmentToCharacterInfoFragment(characterId)
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.locationId
        val name: TextView = view.findViewById(R.id.locationNameTextView)
        val type: TextView = view.findViewById(R.id.locationTypeTextView)
        val dimension: TextView = view.findViewById(R.id.locationDimensionTextView)
        recyclerView = view.findViewById(R.id.locationInfoRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        recyclerView.adapter = adapter
        viewModel.refreshLocation(id)
        viewModel.locationByIdLiveData.observe(viewLifecycleOwner) { response ->
            name.text = response.name
            type.text = response.type
            dimension.text = response.dimension
            adapter.relatedCharactersList = response.residents
        }
    }

}