package com.example.shashankmohabia.atithi.Core.Tour

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.shashankmohabia.atithi.R
import com.example.shashankmohabia.atithi.Core.Tour.TourFragment.TourFragmentInteractionListener
import com.example.shashankmohabia.atithi.Data.Model_Classes.Place
import kotlinx.android.synthetic.main.tour_fragment_item.view.*

class TourFragmentRecyclerViewAdapter(
        private val context: Context,
        private val mValues: MutableList<Place>,
        private val mListener: TourFragmentInteractionListener?)
    : RecyclerView.Adapter<TourFragmentRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Place
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onTourFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tour_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.place_name.text = item.name
        holder.place_location.text = holder.getPlaceString(item.city, item.state)
        Glide.with(context).load(item.image_link).into(holder.place_image)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val place_name: TextView = mView.place_name
        val place_location: TextView = mView.place_location
        var place_image: ImageView = mView.place_image

        fun getPlaceString(city: String, state: String): String {
            return "$city, $state"
        }
    }
}
