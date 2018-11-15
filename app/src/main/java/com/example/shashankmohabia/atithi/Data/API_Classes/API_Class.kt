package com.example.shashankmohabia.atithi.Data.API_Classes

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpPost
import org.json.JSONArray
import org.json.JSONObject

fun AppCompatActivity.getImageLabel(imageBitmap: ByteArray, callback: APIInteractionListener) {
    val URL = "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Prediction/326c0bf6-9eee-4f83-bb3c-e33aa88682a8/image?iterationId=57aa1e86-a6ab-4e10-8e78-82b78d54699a"

    val header = mutableMapOf<String, String>()
    header["Prediction-Key"] = "44ff6064f958491cab7bb4158ec3cbcb"
    header["Content-Type"] = "application/octet-stream"
    try {
        URL.httpPost()
                .header(header)
                .body(imageBitmap)
                .responseJson { request, response, result ->

                    Log.d("API_ka_response", result.get().content)

                    val tagName = try {
                        getDataFromJson(result.get().content, "predictions", "tagName")
                    } catch (e: Exception) {
                        "Error"
                    }
                    callback.onReceive(tagName)
                }
    } catch (e: Exception) {
        callback.onReceive("Error")
    }
}

fun getDataFromJson(content: String, level1Tag: String, level2Tag: String): String {
    val outer = JSONObject(content)
    val predictions = outer.getJSONArray(level1Tag)
    val prediction1 = predictions.getJSONObject(0)
    return prediction1.get(level2Tag).toString()
}

interface APIInteractionListener {
    fun onReceive(label: String)
}
