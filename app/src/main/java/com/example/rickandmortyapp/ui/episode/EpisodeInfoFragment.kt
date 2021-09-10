package com.example.rickandmortyapp.ui.episode

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.character.RelatedCharactersAdapter

class EpisodeInfoFragment : Fragment(R.layout.fragment_episode_info) {
    companion object {
        const val TAG = "EpisodeInfoFragment"
        private const val EPI_KEY = "EPI_KEY"
        fun newInstance(episodeId: Int): EpisodeInfoFragment {
            val episodeInfoFragment = EpisodeInfoFragment()
            val bundle = Bundle()
            bundle.putInt(EPI_KEY, episodeId)
            episodeInfoFragment.arguments = bundle
            return episodeInfoFragment
        }
    }

    private val viewModel: EpisodeInfoViewModel by lazy {
        ViewModelProvider(this).get(EpisodeInfoViewModel::class.java)
    }
    private lateinit var adapter: RelatedCharactersAdapter
    private lateinit var onRelatedCharacterClickListener: OnRelatedCharacterClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onRelatedCharacterClickListener = context as OnRelatedCharacterClickListener
        adapter = RelatedCharactersAdapter {
            onRelatedCharacterClickListener.onCharacterClicked(it)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getInt(EPI_KEY)
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

    interface OnRelatedCharacterClickListener {
        fun onCharacterClicked(id: Int)
    }
}