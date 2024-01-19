package com.gl4.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.projet.adapters.GenreAdapter
import com.gl4.projet.databinding.ActivityMainBinding
import com.gl4.projet.mvvm.GenreViewModel
import com.gl4.projet.network.NetworkManager
import com.gl4.projet.network.NoNetworkException
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    var genreViewModel : GenreViewModel = GenreViewModel(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

/*
        try {

            val networkManager=NetworkManager(this@MainActivity)
            networkManager.observe(this){

                if(!it){

                    Snackbar.make(binding.root, "No internet connection", Snackbar.LENGTH_SHORT).show()
                    //throw NoNetworkException("No internet connection")

                }
                else{
                    loadData()
                    Snackbar.make(binding.root, "Connected to Internet", Snackbar.LENGTH_SHORT).show()

                }
            }


        }catch (e: NoNetworkException){
            Log.e("MainActivity", "NoNetworkException: ${e.message}", e)

        }catch (e: Exception) {
            // Handle other exceptions, if any
            Log.e("MainActivity", "Exception: ${e.message}", e)
        }


*/

/*
        val networkManager=NetworkManager(this@MainActivity)
        networkManager.observe(this){

            if(!it){

                Snackbar.make(binding.root, "No internet connection", Snackbar.LENGTH_SHORT).show()
                //throw NoNetworkException("No internet connection")

            }
            else{

                Snackbar.make(binding.root, "Connected to Internet", Snackbar.LENGTH_SHORT).show()

            }
        }

    */






    }

    fun loadData(){

        genreViewModel.genres.observe(this@MainActivity) {
            if (it != null) {
                binding.rvGenre.adapter =
                    GenreAdapter(genreViewModel.genres.value, this@MainActivity)

            }
        }

        binding.rvGenre.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = GenreAdapter(genreViewModel.genres.value, this@MainActivity)
        }


    }





}