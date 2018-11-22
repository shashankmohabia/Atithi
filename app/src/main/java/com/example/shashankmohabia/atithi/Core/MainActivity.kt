package com.example.shashankmohabia.atithi.Core

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.shashankmohabia.atithi.Core.Community.CommunityFragment
import com.example.shashankmohabia.atithi.Core.Tour.TourFragment
import com.example.shashankmohabia.atithi.Core.Home.LandingFragment
import com.example.shashankmohabia.atithi.Core.Home.Navigation.VrViewFragment
import com.example.shashankmohabia.atithi.Core.Home.PlaceInformationFragment
import com.example.shashankmohabia.atithi.Data.API_Classes.APIInteractionListener
import com.example.shashankmohabia.atithi.Data.API_Classes.getImageLabel
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place.Companion.currentPlace
import com.example.shashankmohabia.atithi.Data.Model_Classes.SubPlace.Companion.subPlacesList
import com.example.shashankmohabia.atithi.Data.ServerClasses.AnotherServerInteractionListener
import com.example.shashankmohabia.atithi.Data.ServerClasses.ServerInteractionListener
import com.example.shashankmohabia.atithi.Data.ServerClasses.getPlaceData
import com.example.shashankmohabia.atithi.Data.ServerClasses.getPlaceList
import com.example.shashankmohabia.atithi.R
import com.example.shashankmohabia.atithi.Utils.Extensions.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_app_bar.*
import kotlinx.android.synthetic.main.main_content.*
import org.jetbrains.anko.toast

class MainActivity :
        AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        TourFragment.TourFragmentInteractionListener,
        CommunityFragment.OnListFragmentInteractionListener,
        PlaceInformationFragment.OnFragmentInteractionListener {

    private val SEARCH_OBJECT_REQUEST_CODE = 1
    private val SEARCH_PLACE_REQUEST_CODE = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(main_toolbar)

        setFloatingButtons()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        setBottomNavBar()
    }

    private fun setFloatingButtons() {

        capture_button.setOnClickListener {
            getCameraIntent(SEARCH_PLACE_REQUEST_CODE)
        }

        navigation_button.setOnClickListener { view ->
            getNavigationIntent()
        }

        search_object_button.setOnClickListener {
            getCameraIntent(SEARCH_OBJECT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SEARCH_PLACE_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            val imageBitArray = imageBitmap.toByteArray(imageBitmap)
            val progressDialog = getProgressDialog()
            getImageLabel(imageBitArray, object : APIInteractionListener {
                override fun onReceive(label: String) {
                    toast(label)
                    val labelList = label.split("&")
                    getPlaceData(labelList[0], labelList[1], object : ServerInteractionListener {
                        override fun onReceivePlaceData() {
                            progressDialog.dismiss()
                            navigation_button.visibility = View.VISIBLE
                            startFragmentTransaction(PlaceInformationFragment(), mainFrame, true)
                        }
                    })
                }
            })
        }

        if (requestCode == SEARCH_OBJECT_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            val imageBitArray = imageBitmap.toByteArray(imageBitmap)
            //toast(imageBitArray.toString())
            val TestString = "encoded_image=$imageBitArray"
            val url = "https://www.google.com/imghp?sbi=1&" + TestString
            searchGoogleImages(url)
        }
    }

    override fun onTourFragmentInteraction(item: Place) {
        val progressDialog = getProgressDialog()
        getPlaceData(item.name + "-" + item.city, "", object : ServerInteractionListener {
            override fun onReceivePlaceData() {
                progressDialog.dismiss()
                navigation_button.visibility = View.VISIBLE
                startFragmentTransaction(PlaceInformationFragment(), mainFrame, true)
            }
        })
    }

    override fun onListFragmentInteraction(item: com.example.shashankmohabia.atithi.Core.Community.dummy.DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setBottomNavBar() {
        startFragmentTransaction(LandingFragment(),mainFrame)
        capture_button.visibility = View.VISIBLE
        search_object_button.visibility = View.VISIBLE
        bottom_navigation.selectedItemId = R.id.home
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    capture_button.visibility = View.VISIBLE
                    search_object_button.visibility = View.VISIBLE
                    if (currentPlace != null) {
                        navigation_button.visibility = View.VISIBLE
                        startFragmentTransaction(PlaceInformationFragment(), mainFrame)
                    } else startFragmentTransaction(LandingFragment(), mainFrame)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tour -> {
                    val progressDialog = getProgressDialog()
                    getPlaceList(object : AnotherServerInteractionListener {
                        override fun onReceivePlaceList() {
                            navigation_button.visibility = View.INVISIBLE
                            capture_button.visibility = View.INVISIBLE
                            search_object_button.visibility = View.INVISIBLE
                            progressDialog.dismiss()
                            startFragmentTransaction(TourFragment(), mainFrame)
                        }
                    })
                }
                R.id.community -> {
                    navigation_button.visibility = View.INVISIBLE
                    capture_button.visibility = View.INVISIBLE
                    search_object_button.visibility = View.INVISIBLE
                    startFragmentTransaction(CommunityFragment(), mainFrame)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}




