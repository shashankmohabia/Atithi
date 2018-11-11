package com.example.shashankmohabia.atithi.Utils.Extensions

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.alert

fun AppCompatActivity.getDialogueBox(t: String, message: String) {
    this.alert(message) {
         title = t
        positiveButton("dismiss"){}
    }.show()
}
