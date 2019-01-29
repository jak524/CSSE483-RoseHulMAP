package edu.rosehulman.knuppja.rosehulmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.building_fragment.view.*
import kotlinx.android.synthetic.main.full_campus_map.view.*


private const val ARG_PIC = "doc"

class FullCampusMapFragment() : Fragment() {





    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.full_campus_map, container, false)
        //Picasso.get().load(getString(R.string.imageurl_full_campus_map)).into(view.full_campus_map_imageView)
        Picasso.get().load(getString(R.string.imageurl_full_campus_map)).into(view.full_campus_map_photoView)
        view.full_campus_map_textView.text = getString(R.string.text_full_campus_map)








        return view
    }





}