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
    private var panoWidgetView: VrPanoramaView? = null

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
}
