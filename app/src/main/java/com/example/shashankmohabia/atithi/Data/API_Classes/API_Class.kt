package com.example.shashankmohabia.atithi.Data.API_Classes

import android.support.v7.app.AppCompatActivity
import java.io.File

fun AppCompatActivity.getImageLabel(imageBitmap: File, callback: APIInteractionListener) {

    callback.onReceive("Shashank")
}

interface APIInteractionListener{
    fun onReceive(label:String)
}
