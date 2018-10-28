package com.example.shashankmohabia.atithi.Utils.Extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.shashankmohabia.atithi.R

fun AppCompatActivity.startFragmentTransaction(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .commit()
}