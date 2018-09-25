package com.example.shashankmohabia.atithi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_boot.*
import java.lang.Thread.sleep

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class BootActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boot)

//        var booticon: ImageView = findViewById(R.id.booticon)
//        var myanim: Animation = AnimationUtils.loadAnimation(this, R.anim.transitions)
//        booticon.startAnimation(myanim)
//        val intent = Intent(this, MainActivity::class.java)
//        Thread {
//            try {
//                sleep(3000);
//            } catch (e: InterruptedException) {
//                e.printStackTrace();
//            } finally {
//                startActivity(intent)
//                finish()
//            }
//
//        }.start()
    }
}