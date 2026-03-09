package com.example.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.model.PlaceData
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork

class PlaceViewModel : ViewModel() {
    val placeLiveData = MutableLiveData<PlaceData>()
}