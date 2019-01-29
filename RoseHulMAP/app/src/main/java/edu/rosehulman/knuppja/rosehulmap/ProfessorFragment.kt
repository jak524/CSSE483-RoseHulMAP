package edu.rosehulman.knuppja.rosehulmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.building_fragment.view.*
import kotlinx.android.synthetic.main.professor_fragment.view.*


private const val ARG_PIC = "doc"

class ProfessorFragment(var adapter: ProfessorListAdapter, var position: Int) : Fragment() {

    private var pic: Building? = null



    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.professor_fragment, container, false)

        //setup image from firstore here
        view.professor_name_view.text = adapter.professors.get(position).name

        view.professor_bio_view.text = adapter.professors.get(position).bio

        view.professor_office_number_view.text = adapter.professors.get(position).officeNumber

        view.professor_building_view.text = adapter.professors.get(position).building








        return view
    }





}