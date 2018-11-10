package com.example.shashankmohabia.atithi.Utils.Extensions

import android.content.Intent
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.R

fun AppCompatActivity.startFragmentTransaction(fragment: Fragment, stateLossAllowed: Boolean = false) {
    if (!stateLossAllowed) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commit()
    } else {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commitAllowingStateLoss()
    }
}

fun AppCompatActivity.getCameraIntent(REQUEST_CODE: Int) {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(packageManager)?.also {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        }
    }
}