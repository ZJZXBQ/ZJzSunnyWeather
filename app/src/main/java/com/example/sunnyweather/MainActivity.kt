package com.example.sunnyweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.sunnyweather.databinding.ActivityMainBinding
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import com.example.sunnyweather.ui.place.PlaceViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var placeViewModel: PlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placeViewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
        binding.getAppDataBtn.setOnClickListener {
            lifecycleScope.launch {
                val responsePlaces = withContext(Dispatchers.IO) {
                    SunnyWeatherNetwork.searchPlaces("成都")
                }
                placeViewModel.placeLiveData.postValue(responsePlaces)
            }
        }
        placeViewModel.placeLiveData.observe(this) { placeData ->
            binding.places.text = placeData.toString()
        }
    }
}