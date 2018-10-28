package com.example.shashankmohabia.atithi.Utils.Extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.R

fun AppCompatActivity.startFragmentTransaction(fragment: Fragment, stateLossAllowed: Boolean = false) {
    if (!stateLossAllowed) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commit()
    }
    else{
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .commitAllowingStateLoss()
    }
}