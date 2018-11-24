package com.example.shashankmohabia.atithi.Utils.Extensions

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.Core.Navigation.NavigationActivity
import android.os.Bundle
import android.widget.FrameLayout


fun AppCompatActivity.startFragmentTransaction(fragment: Fragment, frame: FrameLayout, stateLossAllowed: Boolean = false, data: Any? = null, string: String? = "") {
    if(string != ""){
        val bundle = Bundle()
        bundle.putString("name", string)
        fragment.arguments = bundle
    }
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

fun AppCompatActivity.restartActivity() {
    intent = intent
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    finish()
    overridePendingTransition(0, 0)
    startActivity(intent)
    overridePendingTransition(0, 0)
}

fun AppCompatActivity.getCameraIntent(REQUEST_CODE: Int) {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(packageManager)?.also {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        }
    }
}

fun AppCompatActivity.searchGoogleImages(token: String) {
    val url = "https://images.google.com/searchbyimage?image_url=http://home.iitj.ac.in/~suthar.2/Lakshay/$token"
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun AppCompatActivity.getNavigationIntent() {
    startActivity(Intent(this, NavigationActivity::class.java))
}