package com.example.shashankmohabia.atithi.Utils.Extensions

import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager

fun AppCompatActivity.removeStatusBar() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Any.toggleVisibility() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

