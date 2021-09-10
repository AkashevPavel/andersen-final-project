package com.example.rickandmortyapp.ui.location

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.character.RelatedCharactersAdapter

class LocationInfoFragment : Fragment(R.layout.fragment_location_info) {

    companion object {
        const val TAG = "LocationInfoFragment"
        private const val LOC_KEY = "LOC_KEY"
        fun newInstance(locationId: Int): LocationInfoFragment {
            val locationInfoFragment = LocationInfoFragment()
            val bundle = Bundle()
            bundle.putInt(LOC_KEY, locationId)
            locationInfoFragment.arguments = bundle
            return locationInfoFragment
        }
    }

    private val viewModel: LocationInfoViewModel by lazy {
        ViewModelProvider(this).get(LocationInfoViewModel::class.java)
    }
    private lateinit var adapter: RelatedCharactersAdapter
    private lateinit var onResidentClickListener: OnRelatedCharacterClickListener
    private lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onResidentClickListener = context as OnRelatedCharacterClickListener
        adapter = RelatedCharactersAdapter { onResidentClickListener.onCharacterClicked(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(LOC_KEY)
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

    interface OnRelatedCharacterClickListener {
        fun onCharacterClicked(id: Int)
    }
}