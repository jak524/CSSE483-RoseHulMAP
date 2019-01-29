package edu.rosehulman.knuppja.rosehulmap

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import com.google.firebase.firestore.*


class ProfessorListAdapter(var context: Context?, var listener: ProfessorListFragment.OnPicSelectedListener?, var fm: FragmentManager) : RecyclerView.Adapter<ProfessorViewHolder>() {


    var professors = ArrayList<Professor>()
    private val profRef = FirebaseFirestore.getInstance().collection("professors")

    private lateinit var listenerRegistration: ListenerRegistration






    override fun onBindViewHolder(p0: ProfessorViewHolder, p1: Int) {
        p0.bind(professors[p1])
    }








    override fun getItemCount(): Int {

        return professors.size

    }


//    fun openBuildingFragment(building: Building) {
//        val ft = fm.beginTransaction()
//        var fragment = BuildingFragment(this, fm)
//        ft.add(R.id.fragment_container, fragment)
//        ft.addToBackStack("")
//        ft.commit()
//    }



    fun addSnapshotListener() {
        listenerRegistration = profRef
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
            val prof = Professor.fromSnapshot(documentChange.document)
            when (documentChange.type) {
                DocumentChange.Type.ADDED -> {

                    professors.add(0, prof)
                    notifyItemInserted(0)
                }
                else -> {
                    true
                }

            }
        }
    }



    fun selectPicAt(position: Int) {
        listener?.onPicSelected(professors[position])
    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProfessorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.building_row_view, p0, false)
        return ProfessorViewHolder(view, this)
    }





}