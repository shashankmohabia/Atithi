package com.example.shashankmohabia.atithi.Data.ServerClasses

import android.content.ContentValues.TAG
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener


fun AppCompatActivity.getPlaceData(place: String) {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Places").document(place)
    docRef.get().addOnSuccessListener { documentSnapshot ->
        // val place_data= documentSnapshot.toObject<Place>(Place::class.java)

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
        toast(place_data.name)
    }
    /*
    docRef.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val document = task.result
            if (document!!.exists()) {
                Log.d("satya", "DocumentSnapshot data: " + document.data!!["name"]!!)
            } else {
                Log.d("satya", "No such document")
            }
        } else {
            Log.d("satya", "get failed with ", task.exception)
        }
    }*/
}