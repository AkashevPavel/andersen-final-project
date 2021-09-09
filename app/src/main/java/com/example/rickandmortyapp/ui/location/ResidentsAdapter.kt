package com.example.rickandmortyapp.ui.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Character

class ResidentsAdapter(
    val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ResidentsAdapter.ResidentsHolder>() {

    private var residentsList = mutableListOf<Character>()

    fun setResidents(resident: Character) {
        residentsList.add(resident)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ResidentsHolder(view)
    }

    override fun onBindViewHolder(holder: ResidentsHolder, position: Int) {
        val char = residentsList[position]
        holder.bind(char)
        holder.itemView.setOnClickListener { onClick(char.id) }
    }

    override fun getItemCount() = residentsList.size

    class ResidentsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fullName: TextView = itemView.findViewById(R.id.listItemNameTextView)
        private val avatar: ImageView = itemView.findViewById(R.id.listItemAvatarImageView)
        private val status: TextView = itemView.findViewById(R.id.listItemStatusTextView)
        private val species: TextView = itemView.findViewById(R.id.listItemSpeciesTextView)
        private val gender: ImageView = itemView.findViewById(R.id.listItemGenderImageView)

        fun bind(character: Character?) {
            fullName.text = character?.name
            avatar.load(character?.image)
            when (character?.gender) {
                "male" -> gender.setImageResource(R.drawable.ic_male)
                "female" -> gender.setImageResource(R.drawable.ic_female)
                "genderless" -> gender.setImageResource(R.drawable.ic_genderless)
                else -> gender.setImageResource(R.drawable.ic_gender_unknown)
            }
            status.text = character?.status
            species.text = character?.species
        }
    }
}