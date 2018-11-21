package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.shashankmohabia.atithi.R
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import android.system.Os.shutdown
import android.os.AsyncTask.execute
import android.R.string.cancel
import android.support.annotation.Nullable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [VrViewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [VrViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VrViewFragment : Fragment() {
    lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_vr_view, container, false)
        panoWidgetView = v.findViewById(R.id.pano_view)
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
        val panoImageName = "PANO_20181118_141826_0.jpg"

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

