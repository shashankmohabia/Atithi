package com.example.shashankmohabia.atithi.Data.Model_Classes

data class SubPlace(
        val name: String,
        val description: String,
        val image_link: String,
        val direction_instruction: String
) {
    companion object {
        val subPlacesList: MutableList<SubPlace> = mutableListOf()
    }

}