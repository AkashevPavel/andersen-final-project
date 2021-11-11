package com.example.rickandmortyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.models.Episode

class EpisodesAdapter(
    callBack: DiffUtil.ItemCallback<Episode>,
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<Episode, EpisodesAdapter.EpisodeViewHolder>(callBack) {

    class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.episodesListItemNameTextView)
        private val number: TextView = view.findViewById(R.id.episodesListItemNumberTextView)
        private val airDate: TextView = view.findViewById(R.id.episodesListItemAirDateTextView)
        fun bind(episode: Episode?) {
            name.text = episode?.name
            number.text = episode?.episode
            airDate.text = episode?.airDate
        }
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = getItem(position)
        holder.bind(episode)
        holder.itemView.setOnClickListener { onClick(episode!!.id) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent,false)
        return EpisodeViewHolder(view)
    }
}
object EpisodeComparator : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}