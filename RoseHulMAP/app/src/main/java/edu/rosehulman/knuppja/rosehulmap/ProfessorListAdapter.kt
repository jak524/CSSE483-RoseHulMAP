package edu.rosehulman.knuppja.rosehulmap

import android.content.Context
import android.support.constraint.R.id.parent
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import com.google.firebase.firestore.*


class ProfessorListAdapter(var context: Context?, var listener: ProfessorListFragment.OnPicSelectedListener?, var fm: FragmentManager) : RecyclerView.Adapter<BuildingViewHolder>() {

    var buildings = ArrayList<Building>()
    private val picsRef = FirebaseFirestore.getInstance().collection("professors")

    private lateinit var listenerRegistration: ListenerRegistration






    override fun onBindViewHolder(p0: BuildingViewHolder, p1: Int) {
        p0.bind(buildings[p1])
    }








    override fun getItemCount(): Int {

        return buildings.size

    }


//    fun openBuildingFragment(building: Building) {
//        val ft = fm.beginTransaction()
//        var fragment = BuildingFragment(this, fm)
//        ft.add(R.id.fragment_container, fragment)
//        ft.addToBackStack("")
//        ft.commit()
//    }



    fun addSnapshotListener() {
        listenerRegistration = picsRef
            .addSnapshotListener { querySnapshot, e ->
                if (e != null) {
                    Log.w("tag", "listen error", e)
                } else {
                    processSnapshotChanges(querySnapshot!!)
                }
            }
    }

    private fun processSnapshotChanges(querySnapshot: QuerySnapshot) {
        // Snapshots has documents and documentChanges which are flagged by type,
        // so we can handle C,U,D differently.
        for (documentChange in querySnapshot.documentChanges) {
            val movieQuote = Building.fromSnapshot(documentChange.document)
            when (documentChange.type) {
                DocumentChange.Type.ADDED -> {

                    buildings.add(0, movieQuote)
                    notifyItemInserted(0)
                }
                else -> {
                    true
                }

            }
        }
    }



    fun selectPicAt(position: Int) {
        listener?.onPicSelected(buildings[position])
    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BuildingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.building_row_view, p0, false)
        return ProfessorViewHolder(view, this)
    }





}