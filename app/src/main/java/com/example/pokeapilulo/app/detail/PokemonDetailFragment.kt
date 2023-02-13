package com.example.pokeapilulo.app.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.pokeapilulo.PokeAppApplication
import com.example.pokeapilulo.R
import com.example.pokeapilulo.databinding.FragmentPokemonDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.pokeapilulo.app.detail.StatesRequestPokemonDetail.LoadingDetail
import com.example.pokeapilulo.app.detail.StatesRequestPokemonDetail.SuccesDetail
import com.example.pokeapilulo.app.detail.StatesRequestPokemonDetail.ErrorDetail
import com.example.pokeapilulo.app.model.PokemonDetailModelView
import com.example.pokeapilulo.util.LoadImage
import com.example.pokeapilulo.util.toPointList
import javax.inject.Inject

class PokemonDetailFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    private val args: PokemonDetailFragmentArgs by navArgs()

    private lateinit var fragmentPokemonDetail: FragmentPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as PokeAppApplication).appComponent.inject(this)
        this.isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentPokemonDetail = FragmentPokemonDetailBinding.bind(view)

        setObservers()

        pokemonDetailViewModel.getPokemonByName(args.pokemonSelected.name)
    }

    private fun setObservers() {
        pokemonDetailViewModel.requestState.observe(viewLifecycleOwner){
            when(it){
                is LoadingDetail -> {
                    fragmentPokemonDetail.pokemonDetailAbilitiesTitle.isVisible = it.showLoading.not()
                    fragmentPokemonDetail.pokemonDetailExperienceTitle.isVisible = it.showLoading.not()
                    fragmentPokemonDetail.pokemonDetailMovesTitle.isVisible = it.showLoading.not()
                    fragmentPokemonDetail.pokemonDetailProgressBar.isVisible = it.showLoading
                }
                is SuccesDetail -> updateView(it.pokemon)
                is ErrorDetail -> {
                    dismiss()
                    showErrorDialog(
                        getString(R.string.dialog_error_tile),
                        getString(R.string.dialog_error_msg))
                }
            }
        }
    }

    private fun updateView(pokemon: PokemonDetailModelView){
        LoadImage.loadImageWhitGladeToVector(
            fragmentPokemonDetail.root.context,
            args.pokemonSelected.url,
            fragmentPokemonDetail.pokemonDetailImage
        )
        fragmentPokemonDetail.pokemonDetailName.text = args.pokemonSelected.name
        fragmentPokemonDetail.pokemonDetailExperience.text = pokemon.experience.toString()
        fragmentPokemonDetail.pokemonDetailAbilities.text = pokemon.abilities.toPointList()
        fragmentPokemonDetail.pokemonDetailMoves.text = pokemon.moves.toPointList()
        fragmentPokemonDetail.pokemonDetailReturnBtn.setOnClickListener { dismiss() }
    }

    private fun showErrorDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this.requireActivity())

        builder.setTitle(title)
            .setMessage(message)
            .setNegativeButton(
                R.string.dialog_error_negative_button
            ) { dialog, _ ->
                dialog.dismiss()
            }

        builder
            .setCancelable(false)
            .create()
            .show()
    }

}