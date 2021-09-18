package com.example.rickandmortyapp.ui.character

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersFragment : Fragment(R.layout.fragment_all_characters) {

    private lateinit var adapter: CharactersAdapter

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }
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