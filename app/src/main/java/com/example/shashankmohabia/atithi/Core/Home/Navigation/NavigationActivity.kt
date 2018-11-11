package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.atithi.R
import com.example.shashankmohabia.atithi.Utils.Extensions.getCameraIntent
import com.example.shashankmohabia.atithi.Utils.Extensions.getDialogueBox
import com.example.shashankmohabia.atithi.Utils.Extensions.removeStatusBar
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.navigation_content.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class NavigationActivity : AppCompatActivity() {

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        removeStatusBar()



        setContentView(R.layout.activity_navigation)
        setSupportActionBar(navigation_toolbar)

        setFloatingButtons()

    }

    private fun setFloatingButtons() {

        navigation_description.setOnClickListener {
            getDialogueBox("Main Gate", "nvkjfdbvfb;hvz;hrv;oirhfvofbfvzbvbvzrbzv;.bv.ugrbv.rvihbv.ubf.kzjlurezliurebivrb")
        }

        navigation_direction.setOnClickListener {
            getDialogueBox("Next Spot - Coffee House", "Left after 200 m")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

