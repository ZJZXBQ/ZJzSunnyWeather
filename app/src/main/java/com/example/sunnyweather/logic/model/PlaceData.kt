package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceData(val status: String, val query: String, val placeList: List<Place>)

data class Place(
    val name: String, val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lat: String, val lng: String)