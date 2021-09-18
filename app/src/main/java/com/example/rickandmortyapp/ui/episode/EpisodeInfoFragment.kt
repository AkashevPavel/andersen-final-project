package com.example.rickandmortyapp.ui.episode

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.character.RelatedCharactersAdapter
import com.google.android.material.appbar.MaterialToolbar

class EpisodeInfoFragment : Fragment(R.layout.fragment_episode_info) {

    private val viewModel: EpisodeInfoViewModel by lazy {
        ViewModelProvider(this).get(EpisodeInfoViewModel::class.java)
    }
    private lateinit var adapter: RelatedCharactersAdapter
    private val args by navArgs<EpisodeInfoFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = RelatedCharactersAdapter {
            val action = EpisodeInfoFragmentDirections.actionEpisodeInfoFragmentToCharacterInfoFragment(it)
            findNavController().navigate(action)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.episodeId
        val recycler: RecyclerView = view.findViewById(R.id.episodeInfoRecyclerView)
        val name: TextView = view.findViewById(R.id.episodeNameTextView)
        val airDate: TextView = view.findViewById(R.id.episodeAirDateTextView)
        val number: TextView = view.findViewById(R.id.episodeNumberTextView)
        recycler.adapter = adapter
        viewModel.refreshEpisode(id)
        viewModel.episodeByIdLiveData.observe(viewLifecycleOwner) { episode ->
            name.text = episode.name
            airDate.text = episode.airDate
            number.text = episode.episode
            adapter.relatedCharactersList = episode.characters
        }
    }
}