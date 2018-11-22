package com.example.shashankmohabia.atithi.Utils.Extensions

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.alert
import android.app.ProgressDialog
import com.example.shashankmohabia.atithi.R


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


