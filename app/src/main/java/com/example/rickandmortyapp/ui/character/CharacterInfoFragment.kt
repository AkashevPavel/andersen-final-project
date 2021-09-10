package com.example.rickandmortyapp.ui.character

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.episode.RelatedEpisodesAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class CharacterInfoFragment : Fragment(R.layout.fragment_character_info) {

    companion object {
        const val TAG = "CharacterInfoFragment"
        private const val CHAR_KEY = "CHAR_KEY"
        fun newInstance(id: Int): CharacterInfoFragment {
            val characterInfoFragment = CharacterInfoFragment()
            val bundle = Bundle()
            bundle.putInt(CHAR_KEY, id)
            characterInfoFragment.arguments = bundle
            return characterInfoFragment
        }
    }
    private val viewModel: CharacterInfoViewModel by lazy {
        ViewModelProvider(this).get(CharacterInfoViewModel::class.java)
    }
    private lateinit var adapter: RelatedEpisodesAdapter
    private lateinit var onRelatedLocationClickedListener: OnRelatedLocationClickedListener
    private lateinit var onRelatedEpisodeClickedListener: OnRelatedEpisodeClickedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onRelatedEpisodeClickedListener = context as OnRelatedEpisodeClickedListener
        onRelatedLocationClickedListener = context as OnRelatedLocationClickedListener
        adapter = RelatedEpisodesAdapter {
            onRelatedEpisodeClickedListener.onEpisodeClicked(it)
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

        val id = requireArguments().getInt(CHAR_KEY)
        viewModel.refreshCharacter(id)
        viewModel.characterByIdLiveData.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when(response.gender) {
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
            }
        }
    }
    interface OnRelatedLocationClickedListener{
        fun onLocationClicked(id: Int)
    }
    interface OnRelatedEpisodeClickedListener {
        fun onEpisodeClicked(id: Int)
    }
}