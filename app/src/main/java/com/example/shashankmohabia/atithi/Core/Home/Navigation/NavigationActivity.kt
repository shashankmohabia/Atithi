package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.currentSubPlaceIndex
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.subPlacesList
import com.example.shashankmohabia.atithi.R
import com.example.shashankmohabia.atithi.Utils.Extensions.getDialogueBox
import com.example.shashankmohabia.atithi.Utils.Extensions.removeStatusBar
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.navigation_content.*
import org.jetbrains.anko.toast


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        removeStatusBar()

        setContentView(R.layout.activity_navigation)
        setSupportActionBar(navigation_toolbar)

        setFloatingButtons()

        setImageViewClickListener()

    }

    private fun setImageViewClickListener() {
        if (currentSubPlaceIndex == subPlacesList.size - 1) {
            toast("This is the end to the tour!")
        } else {
            navigation_image.setOnClickListener {
                navigation_image.setImageResource(R.drawable.old_bagan_myanmar)
                currentSubPlaceIndex++
            }
        }
    }

    private fun setFloatingButtons() {

        navigation_description.setOnClickListener {
            getDialogueBox(subPlacesList[currentSubPlaceIndex].name, subPlacesList[currentSubPlaceIndex].description)

        }

        navigation_direction.setOnClickListener {
            getDialogueBox("Next Spot - ${subPlacesList[currentSubPlaceIndex + 1].name}", subPlacesList[currentSubPlaceIndex].direction_instruction)
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

