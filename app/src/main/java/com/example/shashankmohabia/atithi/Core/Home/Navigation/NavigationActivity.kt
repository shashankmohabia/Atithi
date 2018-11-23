package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.app.usage.UsageEvents.Event.NONE
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.currentSubPlaceIndex
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.subPlacesList
import com.example.shashankmohabia.atithi.R
import kotlinx.android.synthetic.main.navigation_main.*
import kotlinx.android.synthetic.main.navigation_content.*
import android.support.constraint.ConstraintLayout
import android.widget.FrameLayout
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.updateCurrentSubPlaceIndex
import android.content.Intent
import android.view.ViewGroup
import com.example.shashankmohabia.atithi.Utils.Extensions.*


class NavigationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        removeStatusBar()

        setContentView(R.layout.navigation_main)

        updateView()

        setSupportActionBar(navigation_toolbar)

        setFloatingButtons()

    }

    private fun updateView() {
        parent_layout.removeAllRectangles()
        Glide.with(this).load(subPlacesList[currentSubPlaceIndex].image_link).into(navigation_image)
        title = subPlacesList[currentSubPlaceIndex].name
        drawRectangle()
    }


    private fun drawRectangle() {
        for (link in subPlacesList[currentSubPlaceIndex].direction_instruction) {
            parent_layout.addView(
                    FrameLayout(this).apply {
                        background = resources.getDrawable(R.drawable.rectangle)
                        layoutParams = ConstraintLayout.LayoutParams(200, 200)
                        setOnClickListener {
                            updateCurrentSubPlaceIndex(link.key)
                            updateView()
                        }
                        x = link.value.first.toFloat()
                        y = link.value.second.toFloat()
                    }
            )
        }
    }

    private fun setFloatingButtons() {

        navigation_description.setOnClickListener {
            getDialogueBox(subPlacesList[currentSubPlaceIndex].name, subPlacesList[currentSubPlaceIndex].description)
        }

        navigation_360view.setOnClickListener {
           startFragmentTransaction(VrViewFragment(), navigation_frame)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        for ((index) in subPlacesList.withIndex()) {
            menu.add(0, index, NONE, subPlacesList[index].name)
        }
        return true
    }

    override fun onBackPressed() {
        if ((findViewById<ViewGroup>(android.R.id.content)).getChildAt(0).id == R.id._360Frame) {
            restartActivity()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        currentSubPlaceIndex = item.itemId.toString().toInt()
        updateView()
        return true
    }
}
