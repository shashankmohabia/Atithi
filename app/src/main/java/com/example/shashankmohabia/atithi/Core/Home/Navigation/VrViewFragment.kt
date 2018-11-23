package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shashankmohabia.atithi.R
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import android.support.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.vr_view_fragment.*

class VrViewFragment : Fragment() {

    companion object {
        private const val VR_IMAGE_URL = "https://firebasestorage.googleapis.com/v0/b/atithi-220608.appspot.com/o/Places%2FMehrangarh_Fort-Jodhpur%2FSubPlaces%2FRang%20Mehal_360.jpg?alt=media&token=adde429c-0bb3-4b69-9122-19479a8c1a22"
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.vr_view_fragment, container, false)
    }
    /*

    override fun onPause() {
        vr_view.pauseRendering()
        super.onPause()
    }

    override fun onResume() {
        vr_view.resumeRendering()
        super.onResume()
    }

    override fun onDestroy() {
        // Destroy the widget and free memory.
        vr_view.shutdown()
        super.onDestroy()
    }*/

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vr_view!!.apply {
            setFlingingEnabled(true)
            setInfoButtonEnabled(true)
            setStereoModeButtonEnabled(true)
            setFullscreenButtonEnabled(true)
        }
        loadContent()
    }

    private fun loadContent() {
        Glide.with(this)
                .asBitmap()
                .load(VR_IMAGE_URL)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val options = VrPanoramaView.Options()
                        options.inputType = VrPanoramaView.Options.TYPE_MONO
                        vr_view!!.loadImageFromBitmap(resource, options)
                    }
                })
    }
}

