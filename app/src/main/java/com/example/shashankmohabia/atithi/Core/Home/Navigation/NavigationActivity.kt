package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.app.usage.UsageEvents.Event.NONE
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import at.lukle.clickableareasimage.ClickableArea
import com.bumptech.glide.Glide
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.currentSubPlaceIndex
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.subPlacesList
import com.example.shashankmohabia.atithi.R
import com.example.shashankmohabia.atithi.Utils.Extensions.getDialogueBox
import com.example.shashankmohabia.atithi.Utils.Extensions.removeStatusBar
import kotlinx.android.synthetic.main.navigation_main.*
import kotlinx.android.synthetic.main.navigation_content.*
import org.jetbrains.anko.toast
import at.lukle.clickableareasimage.ClickableAreasImage
import at.lukle.clickableareasimage.OnClickableAreaClickedListener
import uk.co.senab.photoview.PhotoViewAttacher


class NavigationActivity : AppCompatActivity(), OnClickableAreaClickedListener<ClickableAreasImage> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        removeStatusBar()

        setContentView(R.layout.navigation_main)

        updateView()

        setSupportActionBar(navigation_toolbar)

        setFloatingButtons()

        setImageViewClickListener()

        //setImageViewClicks()

    }

  /*  private fun setImageViewClicks() {
        val clickableAreasImage = ClickableAreasImage(PhotoViewAttacher(navigation_image), this)

        // Initialize your clickable area list
        val clickableAreas = mutableListOf<ClickableArea<Any>>()

        // Define your clickable areas
        // parameter values (pixels): (x coordinate, y coordinate, width, height) and assign an object to it
        clickableAreas.add(ClickableArea(100, 100, 50, 50, "Shashank"))

        clickableAreasImage.clickableAreas = clickableAreas
    }*/

    override fun onClickableAreaTouched(block: ClickableAreasImage?) {
        toast(block.toString())
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
        Glide.with(this).load(subPlacesList[currentSubPlaceIndex].image_link).into(navigation_image)
        title = subPlacesList[currentSubPlaceIndex].name
    }

    private fun setFloatingButtons() {

        navigation_description.setOnClickListener {
            getDialogueBox(subPlacesList[currentSubPlaceIndex].name, subPlacesList[currentSubPlaceIndex].description)
        }

        /*navigation_direction.setOnClickListener {
            if (currentSubPlaceIndex == subPlacesList.size - 1) {
                toast("This is the last spot")
            } else {
                getDialogueBox("Next Spot - ${subPlacesList[currentSubPlaceIndex + 1].name}", subPlacesList[currentSubPlaceIndex].direction_instruction)
            }
        }*/

        navigation_360view.setOnClickListener {
            Snackbar.make(it, "add a 360 view",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()
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
        currentSubPlaceIndex = item.itemId.toString().toInt()
        updateView()
        return true
    }
}

