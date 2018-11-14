package com.example.shashankmohabia.atithi.Data.API_Classes

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.getImageLabel(imageBitmap: Bitmap, callback: APIInteractionListener) {
    callback.onReceive("Shashank")
}

interface APIInteractionListener{
    fun onReceive(label:String)
}
