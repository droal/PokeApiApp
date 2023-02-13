package com.example.pokeapilulo.app.pokemon_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapilulo.PokeAppApplication
import com.example.pokeapilulo.R
import com.example.pokeapilulo.app.model.PokemonModelView
import com.example.pokeapilulo.databinding.FragmentPokemonListBinding
import com.example.pokeapilulo.app.pokemon_list.StatesRequestAllPokemon.LoadingAllpokemon
import com.example.pokeapilulo.app.pokemon_list.StatesRequestAllPokemon.SuccesGetAllPokemon
import com.example.pokeapilulo.app.pokemon_list.StatesRequestAllPokemon.ErrorGetAllPokemon
import com.example.pokeapilulo.util.CheckConnection
import javax.inject.Inject

class PokemonListFragment : Fragment() {

    private var offset = 0
    private var limit = 20
    private lateinit var pokemonList: ArrayList<PokemonModelView>

    @Inject
    lateinit var pokemonListViewModel: PokemonListViewModel

    private lateinit var fragmentPokemonList: FragmentPokemonListBinding

    lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as PokeAppApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentPokemonList = FragmentPokemonListBinding.bind(view)

        pokemonList = ArrayList()
        setRecyclerView()
        setObservers()

        if(CheckConnection.isNetworkAvailable(requireContext())){
            pokemonListViewModel.getAllPokemon(offset, limit)
        }else{
            showErrorDialog(
                getString(R.string.dialog_internet_error_title),
                getString(R.string.dialog_internet_error_msg))
        }
    }


    private fun setRecyclerView() {
        fragmentPokemonList.pokemonListRecyclerView.setHasFixedSize(true)

        pokemonListAdapter = PokemonListAdapter { itemSelected ->
            if(CheckConnection.isNetworkAvailable(requireContext())){
                findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(itemSelected))
            }else{
                showErrorDialog(
                    getString(R.string.dialog_internet_error_title),
                    getString(R.string.dialog_internet_error_msg))
            }
        }
        fragmentPokemonList.pokemonListRecyclerView.adapter = pokemonListAdapter


        fragmentPokemonList.pokemonListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!fragmentPokemonList.pokemonListRecyclerView.canScrollVertically(1)) {
                    loadPageList()
                }
            }
        })
    }

    private fun loadPageList() {
        pokemonListViewModel.getAllPokemon(offset, limit)
    }


    private fun setObservers() {
        pokemonListViewModel.requestState.observe(viewLifecycleOwner){
            when(it){
                is LoadingAllpokemon -> fragmentPokemonList.pokemonListProgressBar.isVisible = it.showLoading
                is SuccesGetAllPokemon -> {
                    val oldCount = pokemonList.size
                    offset += limit
                    pokemonList.addAll(it.pokemonList.result)
                    pokemonListAdapter.updateLis(pokemonList, oldCount, pokemonList.size)
                }
                is ErrorGetAllPokemon -> showErrorDialog(
                    getString(R.string.dialog_error_tile),
                    getString(R.string.dialog_error_msg))
            }
        }
    }

    private fun showErrorDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this.requireActivity())

        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                R.string.dialog_error_positive_button
            ) { dialog, id ->
                if(CheckConnection.isNetworkAvailable(requireContext())){
                    pokemonListViewModel.getAllPokemon(offset, limit)
                }else{
                    showErrorDialog(title,message)
                }
                dialog.dismiss()
            }
            .setNegativeButton(
                R.string.dialog_error_negative_button
            ) { dialog, id ->
                requireActivity().finish()
            }

        builder
            .setCancelable(false)
            .create()
            .show()
    }



}