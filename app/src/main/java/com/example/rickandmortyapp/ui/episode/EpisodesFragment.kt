package com.example.rickandmortyapp.ui.episode

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.adapters.EpisodeComparator
import com.example.rickandmortyapp.ui.adapters.EpisodesAdapter
import com.example.rickandmortyapp.ui.viewmodels.EpisodesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment(R.layout.fragment_all_episodes) {

    private lateinit var adapter: EpisodesAdapter
    private val viewModel: EpisodesViewModel by lazy {
        ViewModelProvider(this).get(EpisodesViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        adapter = EpisodesAdapter(EpisodeComparator) {
            val action = EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeInfoFragment(it)
            findNavController().navigate(action)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.allEpisodesRecyclerView)
        recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodesFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}