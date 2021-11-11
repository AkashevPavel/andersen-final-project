package com.example.rickandmortyapp.ui.character

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.mappers.CharacterMapper
import com.example.rickandmortyapp.ui.adapters.CharacterComparator
import com.example.rickandmortyapp.ui.adapters.CharactersAdapter
import com.example.rickandmortyapp.ui.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_all_characters) {

    private lateinit var adapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = CharactersAdapter(CharacterComparator) { characterId ->
            val action = CharactersFragmentDirections
                .actionCharactersFragmentToCharacterInfoFragment(characterId)
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.allCharactersRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.charactersFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}