package com.example.shashankmohabia.atithi.Data.API_Classes

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpPost
import org.json.JSONArray
import org.json.JSONObject

fun AppCompatActivity.getImageLabel(imageBitmap: ByteArray, callback: APIInteractionListener) {
    val URL = "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Prediction/326c0bf6-9eee-4f83-bb3c-e33aa88682a8/image?iterationId=bcb83a4b-19a7-4b52-bb9c-e59304493d38"

    val header = mutableMapOf<String, String>()
    header["Prediction-Key"] = "44ff6064f958491cab7bb4158ec3cbcb"
    header["Content-Type"] = "application/octet-stream"
    try {
        URL.httpPost()
                .header(header)
                .body(imageBitmap)
                .responseJson { request, response, result ->
                    /*Log.d("API_ka_response", request.toString())
                    Log.d("API_ka_response", response.toString())*/
                    Log.d("API_ka_response", result.get().content)

                    val tagName = try {
                        val outer = JSONObject(result.get().content)
                        val predictions = outer.getJSONArray("predictions")
                        val prediction1 = predictions.getJSONObject(0)
                        prediction1.get("tagName").toString()
                    } catch (e: Exception) {
                        "Error"
                    }
                    callback.onReceive(tagName)
                }
    } catch (e: Exception) {
        callback.onReceive("Connection Error")
    }
}

interface APIInteractionListener {
    fun onReceive(label: String)
}
