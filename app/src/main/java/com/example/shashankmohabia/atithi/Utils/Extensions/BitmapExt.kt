package com.example.shashankmohabia.atithi.Utils.Extensions

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.example.shashankmohabia.atithi.Core.MainActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 90, stream)
    return stream.toByteArray()
}

fun Bitmap.getFilePath(context:Context): String {
    val filesDir = context.cacheDir
    val imageFile = File(filesDir, "image.jpg")

    val os: OutputStream
    try {
        os = FileOutputStream(imageFile)
        this.compress(Bitmap.CompressFormat.JPEG, 100, os)
        os.flush()
        os.close()
    } catch (e: Exception) {
        Log.d("Lakshya", "Error writing bitmap", e)
    }
    Log.d("Lakshya", "Successful")
    return imageFile.path
}

