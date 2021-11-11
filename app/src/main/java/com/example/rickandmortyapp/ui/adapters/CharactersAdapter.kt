package com.example.rickandmortyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.models.Character

class CharactersAdapter(
    callBack: DiffUtil.ItemCallback<Character>,
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(callBack) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item!!.id) }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CharacterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }


    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fullName: TextView = itemView.findViewById(R.id.charactersListItemNameTextView)
        private val avatar: ImageView = itemView.findViewById(R.id.charactersListItemAvatarImageView)
        private val status: TextView = itemView.findViewById(R.id.charactersListItemStatusTextView)
        private val species: TextView = itemView.findViewById(R.id.charactersListItemSpeciesTextView)
        private val gender: ImageView = itemView.findViewById(R.id.charactersListItemGenderImageView)

        fun bind(character: Character?) {
            fullName.text = character?.name
            avatar.load(character?.image)
            when (character?.gender) {
                "Female" -> gender.setImageResource(R.drawable.ic_female)
                "Unknown" -> gender.setImageResource(R.drawable.ic_gender_unknown)
                "Genderless" -> gender.setImageResource(R.drawable.ic_genderless)
            }
            status.text = character?.status
            species.text = character?.species
        }
    }

}

object CharacterComparator : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}