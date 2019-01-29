

package edu.rosehulman.knuppja.rosehulmap

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class ProfessorListFragment(var fm: FragmentManager) : Fragment() {
    private var listener: OnPicSelectedListener? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val recyclerView = inflater.inflate(R.layout.building_fragment_list, container, false) as RecyclerView
        val adapter = ProfessorListAdapter(context, listener, fm)
        recyclerView.adapter = adapter


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        adapter.addSnapshotListener()


        return recyclerView
    }





//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnPicSelectedListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnPicSelectedListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    interface OnPicSelectedListener {
        fun onPicSelected(building: Professor)
    }

}