package com.example.shashankmohabia.atithi.Utils.Extensions

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.alert
import android.app.ProgressDialog



fun AppCompatActivity.getDialogueBox(t: String, message: String) {
    this.alert(message) {
         title = t

    }.show()
}


fun AppCompatActivity.getProgressDialog(): ProgressDialog {
    return ProgressDialog(this)
            .apply {
                setMessage("Loading")
                show()
            }
}


