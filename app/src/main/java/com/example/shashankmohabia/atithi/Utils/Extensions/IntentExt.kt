package com.example.shashankmohabia.atithi.Utils.Extensions

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.Core.Home.Navigation.NavigationActivity
import com.example.shashankmohabia.atithi.R
import android.R.attr.fragment
import android.os.Bundle
import android.os.Parcelable
import android.widget.FrameLayout


fun AppCompatActivity.startFragmentTransaction(fragment: Fragment, frame: FrameLayout, stateLossAllowed: Boolean = false, data: Any? = null) {
    if (!stateLossAllowed) {
        supportFragmentManager.beginTransaction()
                .replace(frame.id, fragment)
                .addToBackStack(null)
                .commit()
    } else {
        supportFragmentManager.beginTransaction()
                .replace(frame.id, fragment)
                .addToBackStack(null)
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

fun AppCompatActivity.searchGoogleImages(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun AppCompatActivity.getNavigationIntent() {
    startActivity(Intent(this, NavigationActivity::class.java))
}