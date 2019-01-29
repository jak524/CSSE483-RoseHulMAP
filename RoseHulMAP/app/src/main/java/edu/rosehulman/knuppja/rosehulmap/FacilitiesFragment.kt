package edu.rosehulman.knuppja.rosehulmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.building_fragment.view.*


private const val ARG_PIC = "doc"

class FacilitiesFragment() : Fragment() {

    private var pic: Building? = null



    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.facilities_main, container, false)










        return view
    }





}