package com.example.shashankmohabia.atithi.Core.Home.Navigation

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log

import com.google.vr.sdk.widgets.pano.VrPanoramaView

import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference

/**
 * @author Lakshya Garg (garg.2@iitj.ac.in)
 */
class ImageLoaderTask(view: VrPanoramaView, private val viewOptions: VrPanoramaView.Options, private val assetName: String) : AsyncTask<AssetManager, Void, Bitmap>() {
    private val viewReference: WeakReference<VrPanoramaView>

    init {
        viewReference = WeakReference(view)
    }


    override fun doInBackground(vararg params: AssetManager): Bitmap? {
        val assetManager = params[0]

        if (assetName == lastName && lastBitmap.get() != null) {
            return lastBitmap.get()
        }

        try {
            assetManager.open(assetName).use { istr ->
                val b = BitmapFactory.decodeStream(istr)
                lastBitmap = WeakReference(b)
                lastName = assetName
                return b
            }
        } catch (e: IOException) {
            Log.e(TAG, "Could not decode default bitmap: $e")
            return null
        }

    }

    override fun onPostExecute(bitmap: Bitmap?) {
        val vw = viewReference.get()
        if (vw != null && bitmap != null) {
            vw.loadImageFromBitmap(bitmap, viewOptions)
        }
    }

    companion object {
        private val TAG = "ImageLoaderTask"
        private var lastBitmap = WeakReference<Bitmap>(null)
        private var lastName: String? = null
    }

}
