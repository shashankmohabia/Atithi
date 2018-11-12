package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.app.usage.UsageEvents.Event.NONE
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.currentSubPlaceIndex
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.subPlacesList
import com.example.shashankmohabia.atithi.R
import com.example.shashankmohabia.atithi.Utils.Extensions.getDialogueBox
import com.example.shashankmohabia.atithi.Utils.Extensions.removeStatusBar
import kotlinx.android.synthetic.main.navigation_main.*
import kotlinx.android.synthetic.main.navigation_content.*
import org.jetbrains.anko.toast


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        removeStatusBar()

        setContentView(R.layout.navigation_main)

        updateView()

        setSupportActionBar(navigation_toolbar)

        setFloatingButtons()

        setImageViewClickListener()

    }

    private fun setImageViewClickListener() {
        navigation_image.setOnClickListener {
            if (currentSubPlaceIndex == subPlacesList.size - 1) {
                toast("This is the end to the tour!")
            } else {
                currentSubPlaceIndex++
                updateView()
            }
        }
    }

    private fun updateView() {
        navigation_image.setImageResource(R.drawable.old_bagan_myanmar)
        title = subPlacesList[currentSubPlaceIndex].name
    }

    private fun setFloatingButtons() {

        navigation_description.setOnClickListener {
            getDialogueBox(subPlacesList[currentSubPlaceIndex].name, subPlacesList[currentSubPlaceIndex].description)

        }

        navigation_direction.setOnClickListener {
            if (currentSubPlaceIndex == subPlacesList.size - 1) {
                toast("This is the last spot")
            } else {
                getDialogueBox("Next Spot - ${subPlacesList[currentSubPlaceIndex + 1].name}", subPlacesList[currentSubPlaceIndex].direction_instruction)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        for ((index) in subPlacesList.withIndex()) {
            menu.add(0, index, NONE, subPlacesList[index].name)
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        currentSubPlaceIndex = 0
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        toast(item.itemId.toString())
        currentSubPlaceIndex = item.itemId.toString().toInt()
        updateView()
        return true

    }
}

