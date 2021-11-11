package com.example.rickandmortyapp.ui.character

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.adapters.RelatedEpisodesAdapter
import com.example.rickandmortyapp.ui.viewmodels.CharacterInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment(R.layout.fragment_character_info) {

    private val viewModel: CharacterInfoViewModel by viewModels()
    private lateinit var adapter: RelatedEpisodesAdapter
    private val args by navArgs<CharacterInfoFragmentArgs>()

    override fun onAttach(context: Context) {

        super.onAttach(context)
        adapter = RelatedEpisodesAdapter { episodeId ->
            val action = CharacterInfoFragmentDirections
                .actionCharacterInfoFragmentToEpisodeInfoFragment(episodeId)
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<TextView>(R.id.nameTextView)
        val status = view.findViewById<TextView>(R.id.statusTextView)
        val origin = view.findViewById<TextView>(R.id.originTextView)
        val location = view.findViewById<TextView>(R.id.locationTextView)
        val species = view.findViewById<TextView>(R.id.speciesTextView)
        val gender = view.findViewById<ImageView>(R.id.genderImageView)
        val avatar = view.findViewById<ImageView>(R.id.avatarImageView)
        val recycler = view.findViewById<RecyclerView>(R.id.characterInfoRecyclerView)

        recycler.adapter = adapter
        viewModel.refreshCharacter(args.characterId)
        viewModel.characterByIdLiveData.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response.gender) {
                    "Male" -> gender.setImageResource(R.drawable.ic_male)
                    "Female" -> gender.setImageResource(R.drawable.ic_female)
                    "Genderless" -> gender.setImageResource(R.drawable.ic_genderless)
                    else -> gender.setImageResource(R.drawable.ic_gender_unknown)
                }
                name.text = response.name
                status.text = response.status
                location.text = response.location.name
                origin.text = response.origin.name
                species.text = response.species
                avatar.load(response.image)
                adapter.relatedEpisodesList = response.episode
                location.setOnClickListener {
                    val action = CharacterInfoFragmentDirections
                        .actionCharacterInfoFragmentToLocationInfoFragment(getIdFromUrl(response.location.url))
                    findNavController().navigate(action)
                }

                origin.setOnClickListener {
                    if (origin.text != "unknown") {
                        val action = CharacterInfoFragmentDirections
                            .actionCharacterInfoFragmentToLocationInfoFragment(getIdFromUrl(response.origin.url))
                        findNavController().navigate(action)
                    } else {
                        showToast("Sorry we don't know where it is :(")
                    }
                }

            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    private fun getIdFromUrl(url: String): Int {
        return url.substring(startIndex = url.lastIndexOf("/") + 1).toInt()
    }
}