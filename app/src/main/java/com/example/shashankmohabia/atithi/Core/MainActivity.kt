package com.example.shashankmohabia.atithi.Core

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shashankmohabia.atithi.Core.Community.CommunityFragment
import com.example.shashankmohabia.atithi.Core.Explore.ExploreFragment
import com.example.shashankmohabia.atithi.Core.Explore.dummy.DummyContent
import com.example.shashankmohabia.atithi.Core.Home.LandingFragment
import com.example.shashankmohabia.atithi.Core.Home.PlaceInformationFragment
import com.example.shashankmohabia.atithi.R
import com.example.shashankmohabia.atithi.Utils.Extensions.startFragmentTransaction
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_app_bar.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity :
        AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        ExploreFragment.OnListFragmentInteractionListener,
        CommunityFragment.OnListFragmentInteractionListener,
        PlaceInformationFragment.OnFragmentInteractionListener{

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        setCaptureButton()
        setNavigationButton()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        setBottomNavBar()
    }

    private fun setNavigationButton() {
        navigation_button.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun setCaptureButton() {
        capture_button.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            /* val imageBitmap = data!!.extras.get("data") as Bitmap
             cameraResult.setImageBitmap(imageBitmap)*/
            startFragmentTransaction(PlaceInformationFragment(), true)
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListFragmentInteraction(item: com.example.shashankmohabia.atithi.Core.Community.dummy.DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setBottomNavBar() {
        bottom_navigation.selectedItemId = R.id.home
        startFragmentTransaction(LandingFragment())
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    startFragmentTransaction(PlaceInformationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.explore -> {
                    startFragmentTransaction(ExploreFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.community -> {
                    startFragmentTransaction(CommunityFragment())
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
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
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
