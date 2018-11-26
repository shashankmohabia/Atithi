package com.example.shashankmohabia.atithi.Data.ServerClasses

import android.content.ContentValues.TAG
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place.Companion.currentPlace
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place.Companion.placeList
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.updateCurrentSubPlaceIndex
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.subPlacesList

fun AppCompatActivity.getPlaceData(place: String, subplace: String, callback: ServerInteractionListener) {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Places").document(place)
    docRef.get().addOnSuccessListener { documentSnapshot ->
        //val place_data= documentSnapshot.toObject<Place>(Place::class.java)

        currentPlace = Place(
                documentSnapshot.data!!["name"].toString(),
                documentSnapshot.data!!["city"].toString(),
                documentSnapshot.data!!["state"].toString(),
                documentSnapshot.data!!["country"].toString(),
                documentSnapshot.data!!["description"].toString(),
                documentSnapshot.data!!["image_link"].toString(),
                documentSnapshot.data!!["opening_time"].toString(),
                documentSnapshot.data!!["closing_time"].toString()
        )
    }

    subPlacesList.clear()
    docRef.collection("SubPlaces")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val subPlace = SubPlace(
                                document.data["name"].toString(),
                                document.data["description"].toString(),
                                document.data["image_link"].toString(),
                                document.data["360_image_url"].toString(),
                                getDirectionLinks(document.data["direction_instruction"].toString())
                        )
                        subPlacesList.add(subPlace)
                        //Log.d("direction", subPlace.name + " " + subPlace.direction_instruction.size.toString())
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
                //subPlacesList.reverse()
                updateCurrentSubPlaceIndex(subplace)
                callback.onReceivePlaceData()
            }
}

fun getDirectionLinks(directionString: String): MutableMap<String, Pair<Int, Int>> {
    val directionLinkMap: MutableMap<String, Pair<Int, Int>> = mutableMapOf()
    if (directionString != "") {
        val list = directionString.split("&")
        for (item in list) {
            val s = item.split("-")
            directionLinkMap[s[0]] = Pair(s[1].toInt(), s[2].toInt())
        }
    }
    //Log.d("qwertyuiop", directionLinkMap.size.toString())
    return directionLinkMap
}

fun AppCompatActivity.getPlaceList(callback: AnotherServerInteractionListener): Boolean {
    placeList.clear()
    FirebaseFirestore.getInstance().collection("Places").get().addOnCompleteListener {
        if (it.isSuccessful) {
            for (document in it.result!!) {
                val place = Place(
                        document.data["name"].toString(),
                        document.data["city"].toString(),
                        document.data["state"].toString(),
                        document.data["country"].toString(),
                        document.data["description"].toString(),
                        document.data["image_link"].toString(),
                        document.data["opening_time"].toString(),
                        document.data["closing_time"].toString()
                )
                placeList.add(place)
                Log.d("ajat", document.id + " => " + document.data)
                Log.d("ajat", placeList.size.toString())
            }
            callback.onReceivePlaceList()
        }
    }
    return true
}

interface ServerInteractionListener {
    fun onReceivePlaceData()
}

interface AnotherServerInteractionListener {
    fun onReceivePlaceList()
}