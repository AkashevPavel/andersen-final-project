package com.example.rickandmortyapp.ui.episode

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

class EpisodesFragment : Fragment(R.layout.fragment_all_episodes) {

    companion object {
        fun newInstance() = EpisodesFragment()
        const val TAG = "EpisodesFragment"
    }
    private lateinit var adapter: EpisodesAdapter
    private lateinit var onEpisodeClickedListener: OnEpisodeClickedListener
    private val viewModel: EpisodesViewModel by lazy {
        ViewModelProvider(this).get(EpisodesViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onEpisodeClickedListener = context as OnEpisodeClickedListener
        adapter = EpisodesAdapter(EpisodeComparator) {
            onEpisodeClickedListener.onEpisodeClicked(it)
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

    interface OnEpisodeClickedListener {
        fun onEpisodeClicked(id: Int)
    }
}