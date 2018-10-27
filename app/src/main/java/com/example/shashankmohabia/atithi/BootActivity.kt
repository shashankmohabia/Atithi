package com.example.shashankmohabia.atithi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.lang.Thread.sleep

class BootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boot)

        val intent = Intent(this, MainActivity::class.java)
        Thread{
            try {
                sleep(3000)
            }
            catch (e:InterruptedException){
                e.printStackTrace()
            }
            finally {
                startActivity(intent)
                finish()
            }
        }.start()
    }
}
