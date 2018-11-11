package com.example.shashankmohabia.atithi.Data.ServerClasses

import android.content.ContentValues.TAG
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace
import org.jetbrains.anko.doAsync


fun AppCompatActivity.getPlaceData(place: String, callback: ServerInteractionListener) {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Places").document(place)
    docRef.get().addOnSuccessListener { documentSnapshot ->
        //val place_data= documentSnapshot.toObject<Place>(Place::class.java)

        val place_data = Place(
                documentSnapshot.data!!["name"].toString(),
                documentSnapshot.data!!["city"].toString(),
                documentSnapshot.data!!["state"].toString(),
                documentSnapshot.data!!["country"].toString(),
                documentSnapshot.data!!["description"].toString(),
                documentSnapshot.data!!["image_link"].toString(),
                documentSnapshot.data!!["opening_time"].toString(),
                documentSnapshot.data!!["closing_time"].toString()
        )

        //Log.d("eventlog", documentSnapshot.data.toString())

        callback.onReceivePlaceData(place_data)
    }
     val subPlacesList: MutableList<SubPlace> = mutableListOf()
    docRef.collection("SubPlaces")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val subPlace = SubPlace(
                                document.data["name"].toString(),
                                document.data["description"].toString(),
                                document.data["image_link"].toString(),
                                document.data["direction_instruction"].toString()
                        )
                        subPlacesList.add(subPlace)
                        Log.d("eventlog", document.id + " => " + document.data)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
                callback.onReceiveSubPlaceData(subPlacesList)

            }

}

interface ServerInteractionListener{
    fun onReceivePlaceData(data:Place)
    fun onReceiveSubPlaceData(data:MutableList<SubPlace>)
}