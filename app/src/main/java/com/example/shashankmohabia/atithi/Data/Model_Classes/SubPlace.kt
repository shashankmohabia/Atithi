package com.example.shashankmohabia.atithi.Data.Model_Classes

import android.util.Log

data class SubPlace(
        val name: String,
        val description: String,
        val image_link: String,
        val direction_instruction: String
) {
    companion object {
        val subPlacesList: MutableList<SubPlace> = mutableListOf()
        var currentSubPlaceIndex = 0
        fun initializeCurrentSubPlaceIndex(currentSubPlace: String) {
            val index = subPlacesList.indexOf(subPlacesList.find { it.name == currentSubPlace })
            if (index != -1) currentSubPlaceIndex = index
            //Log.d("Shreshth", subPlacesList.indexOf(subPlacesList.find { it.name == currentSubPlace }).toString())
        }
    }
}