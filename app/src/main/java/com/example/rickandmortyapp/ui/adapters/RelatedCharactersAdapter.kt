package com.example.rickandmortyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.models.Character

class RelatedCharactersAdapter(
    val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RelatedCharactersAdapter.RelatedCharactersHolder>() {

    var relatedCharactersList = listOf<Character>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedCharactersHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return RelatedCharactersHolder(view)
    }

    override fun onBindViewHolder(holder: RelatedCharactersHolder, position: Int) {
        val resident = relatedCharactersList[position]
        holder.bind(resident)
        holder.itemView.setOnClickListener { onClick(resident.id) }
    }

    override fun getItemCount() = relatedCharactersList.size

    class RelatedCharactersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fullName: TextView = itemView.findViewById(R.id.charactersListItemNameTextView)
        private val avatar: ImageView = itemView.findViewById(R.id.charactersListItemAvatarImageView)
        private val status: TextView = itemView.findViewById(R.id.charactersListItemStatusTextView)
        private val species: TextView = itemView.findViewById(R.id.charactersListItemSpeciesTextView)
        private val gender: ImageView = itemView.findViewById(R.id.charactersListItemGenderImageView)

        fun bind(character: Character?) {
            fullName.text = character?.name
            avatar.load(character?.image)
            when (character?.gender) {
                "Male" -> gender.setImageResource(R.drawable.ic_male)
                "Female" -> gender.setImageResource(R.drawable.ic_female)
                "Genderless" -> gender.setImageResource(R.drawable.ic_genderless)
                else -> gender.setImageResource(R.drawable.ic_gender_unknown)
            }
            status.text = character?.status
            species.text = character?.species
        }
    }
}