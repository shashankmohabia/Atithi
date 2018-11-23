package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.shashankmohabia.atithi.R
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import android.support.annotation.Nullable
import android.util.Log


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VrViewFragment : Fragment() {
    lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.vr_view_fragment, container, false)
        panoWidgetView = v.findViewById(R.id.vr_view)
        Log.d("Sohail", this.arguments?.getString("name"))
        return v
    }

    override fun onPause() {
        panoWidgetView.pauseRendering()
        super.onPause()
    }

    override fun onResume() {
        panoWidgetView.resumeRendering()
        super.onResume()
    }

    override fun onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView.shutdown()
        super.onDestroy()
    }

    @Synchronized
    private fun loadPanoImage() {
        var task = backgroundImageLoaderTask
        if (task != null && !task.isCancelled()) {
            // Cancel any task from a previous loading.
            task.cancel(true)
        }

        // pass in the name of the image to load from assets.
        val viewOptions = VrPanoramaView.Options()
        viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO

        // use the name of the image in the assets/ directory.
        val panoImageName = this.arguments?.getString("name") + ".jpg"

        // create the task passing the widget view and call execute to start.
        task = ImageLoaderTask(panoWidgetView, viewOptions, panoImageName)
        task.execute(activity!!.assets)
        backgroundImageLoaderTask = task
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadPanoImage()
    }
}

