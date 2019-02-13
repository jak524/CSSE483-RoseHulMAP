package edu.rosehulman.knuppja.rosehulmap

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import com.google.firebase.firestore.*


class RoomListAdapter(var context: Context?, var listener: RoomListFragment.OnPicSelectedListener?, var fm: FragmentManager) : RecyclerView.Adapter<RoomViewHolder>() {


    var rooms = ArrayList<Room>()
    private val roomRef = FirebaseFirestore.getInstance().collection("rooms")

    private lateinit var listenerRegistration: ListenerRegistration






    override fun onBindViewHolder(p0: RoomViewHolder, p1: Int) {
        p0.bind(rooms[p1])
    }








    override fun getItemCount(): Int {

        return rooms.size

    }


//    fun openBuildingFragment(building: Building) {
//        val ft = fm.beginTransaction()
//        var fragment = BuildingFragment(this, fm)
//        ft.add(R.id.fragment_container, fragment)
//        ft.addToBackStack("")
//        ft.commit()
//    }



    fun addSnapshotListener() {
        listenerRegistration = roomRef
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
            val room = Room.fromSnapshot(documentChange.document)
            when (documentChange.type) {
                DocumentChange.Type.ADDED -> {

                    rooms.add(0, room)
                    notifyItemInserted(0)
                }
                else -> {
                    true
                }

            }
        }
    }



//    fun selectPicAt(position: Int) {
//        listener?.onPicSelected(rooms[position])
//    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RoomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.building_row_view, p0, false)
        return RoomViewHolder(view, this)
    }





}