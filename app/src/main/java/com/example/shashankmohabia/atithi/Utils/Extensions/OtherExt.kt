package com.example.shashankmohabia.atithi.Utils.Extensions

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


fun Any.toByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
    return stream.toByteArray()
}
