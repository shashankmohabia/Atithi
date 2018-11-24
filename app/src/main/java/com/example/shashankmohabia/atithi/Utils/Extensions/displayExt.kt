package com.example.shashankmohabia.atithi.Utils.Extensions

import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout


fun AppCompatActivity.removeStatusBar() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.removeAllRectangles() {
    for (j in 0 until this.childCount) {
        for (i in 0 until this.childCount) {
            if (this.getChildAt(i) is FrameLayout) {
                //Log.d("asdfghjkl", "asdfghjkl")
                this.removeView(this.getChildAt(i))
            }
        }
    }
}

