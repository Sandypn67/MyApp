package com.example.esiea3a.presentation.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea3a.R
import com.example.esiea3a.presentation.Singleton
import com.example.esiea3a.presentation.api.PokeApi
import com.example.esiea3a.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader : ProgressBar
    private lateinit var error_gen : TextView

    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)
    private val viewModel: PokemonListViewModel by viewModels()


    //methode pour faire du cache mais pas utilisée ici
    //private val sharedPref : SharedPreferences? = activity?.getSharedPreferences("app", Context.MODE_PRIVATE)

    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerview)
        loader = view.findViewById(R.id.pokemon_loader)
        error_gen = view.findViewById(R.id.pokemon_error)
        recyclerView.apply {
            layoutManager = this@PokemonListFragment.layoutManager
            adapter =this@PokemonListFragment.adapter
        }
        viewModel.pokeList.observe(viewLifecycleOwner, Observer { pokemonModel ->
            loader.isVisible = pokemonModel is PokemonLoader
            error_gen.isVisible = pokemonModel is PokemonError
            if(pokemonModel is PokemonSuccess) {
                adapter.updateList(pokemonModel.pokeList)
            }

        })

//        val list = getListFromCache()
//        if(list.isEmpty()){
//            callApi();
//        }
//        else{
//            showList(list)
//        }
//
//    }
//
//    private fun getListFromCache(): List<Pokemon> {
//        //le reste de la methode à appeler pour faire du cache
//        //  sharedPref.
//        TODO("Not yet implemented")
//    }
//    private fun saveListIntoCache() {
//        TODO("Not yet implemented")
//    }

  //  private fun callApi() {
//        Singleton.pokeApi.getPokemonList().enqueue(object : Callback<PokemonListResponse> {
//            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
//                if (response.isSuccessful && response.body() != null) {
//                    val pokemonResponse = response.body()!!
//                    adapter.updateList(pokemonResponse.results)
////                    saveListIntoCache()
////                    showList(pokemonResponse.results)
//                }
//            }
//        })

        //        val pokeList = arrayListOf<Pokemon>().apply {
        //            add(Pokemon("Pikachu"))
        //            add(Pokemon("Salamèche"))
        //            add(Pokemon("Carapuce"))
        //            add(Pokemon("Bulbizarre"))
        //        }
    }

    private fun showList(pokeList: List<Pokemon>) {
        adapter.updateList(pokeList)
    }
    private fun onClickedPokemon(id: Int) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragment, bundleOf("pokemonId" to (id+1)))
    }
}