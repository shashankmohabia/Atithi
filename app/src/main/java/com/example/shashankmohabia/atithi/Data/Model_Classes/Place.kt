package com.example.shashankmohabia.atithi.Data.Model_Classes

data class Place(
        val name: String,
        val city: String,
        val state: String,
        val country: String,
        val description: String,
        val image_link: String,
        val opening_time: String,
        val closing_time: String
) {
    companion object {
        lateinit var currentPlace: Place

        var placeList = mutableListOf<Place>()
    }
}