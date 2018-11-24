package com.example.shashankmohabia.atithi.Data.ServerClasses

import android.content.Context
import android.util.Log
import java.io.BufferedInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.URL

import org.jetbrains.anko.doAsync

fun uploadPhotoToServer(imgPath: String, ctx: Context, callback:ImageUpload) {

    val apiUrl = "http://home.iitj.ac.in/~suthar.2/main.php"
    val sourceFile = File(imgPath)

    ctx.doAsync {

        HttpURLConnection.setFollowRedirects(false)
        val fileName = "image_" + System.currentTimeMillis() + ".jpg"


        try {
            val connection = URL(apiUrl).openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            val boundary = "---------------------------boundary"
            val tail = "\r\n--$boundary--\r\n"
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")
            connection.doOutput = true
            connection.doInput = true

            val metadataPart = ("--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"metadata\"\r\n\r\n"
                    + "" + "\r\n")

            val fileHeader1 = ("--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"bill\"; filename=\""
                    + fileName + "\"\r\n"
                    + "Content-Type: application/octet-stream\r\n"
                    + "Content-Transfer-Encoding: binary\r\n")

            val fileLength = sourceFile.length() + tail.length
            val fileHeader2 = "Content-length: $fileLength\r\n"
            val fileHeader = fileHeader1 + fileHeader2 + "\r\n"
            val stringData = metadataPart + fileHeader


            val requestLength = stringData.length + fileLength
            connection.setRequestProperty("Content-length", "" + requestLength)
            connection.setFixedLengthStreamingMode(requestLength.toInt())
            connection.connect()

            val out = DataOutputStream(connection.outputStream)
            out.writeBytes(stringData)
            out.flush()
            var bytesRead: Int
            val buf = ByteArray(1024)
            val bufInput = BufferedInputStream(FileInputStream(sourceFile))
            bytesRead = bufInput.read(buf)
            while ((bytesRead) != -1) {
                // write output
                out.write(buf, 0, bytesRead)
                out.flush()
                bytesRead = bufInput.read(buf)
            }

            // Write closing boundary and close stream
            out.writeBytes(tail)
            out.flush()
            out.close()
            val serverResponseCode = connection.responseCode
            val serverResponseMessage = connection.responseMessage
            Log.d("Lakshya", serverResponseCode.toString())
            Log.d("Lakshya", serverResponseMessage)
            connection.disconnect()

        } catch (e: Exception) {
            Log.d("Lakshya", e.toString())
            // Exception
        }

        callback.onImageUpload(fileName)

    }
}

interface ImageUpload {
    fun onImageUpload(token: String)
}

