package com.example.rickandmortyapp.ui.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.models.Episode

class RelatedEpisodesAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RelatedEpisodesAdapter.RelatedEpisodesHolder>() {

    var relatedEpisodesList = listOf<Episode>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class RelatedEpisodesHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.episodesListItemNameTextView)
        private val airDate: TextView = view.findViewById(R.id.episodesListItemAirDateTextView)
        private val number: TextView = view.findViewById(R.id.episodesListItemNumberTextView)

        fun bind(episode: Episode) {
            name.text = episode.name
            airDate.text = episode.airDate
            number.text = episode.episode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedEpisodesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        return RelatedEpisodesHolder(view)
    }

    override fun onBindViewHolder(holder: RelatedEpisodesHolder, position: Int) {
        val episode = relatedEpisodesList[position]
        holder.bind(episode)
        holder.itemView.setOnClickListener { onClick(episode.id) }
    }

    override fun getItemCount() = relatedEpisodesList.size
}