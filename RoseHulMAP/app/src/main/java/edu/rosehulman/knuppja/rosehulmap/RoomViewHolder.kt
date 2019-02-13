package edu.rosehulman.knuppja.rosehulmap

import android.app.PendingIntent.getActivity
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.building_row_view.view.*

class RoomViewHolder(itemView: View, var adapter: RoomListAdapter) : RecyclerView.ViewHolder(itemView) {


    fun bind(room: Room) {
        itemView.building_name_text_view.text = room.name

        itemView.building_fact_text_view.text = room.type



        itemView.setOnClickListener {
            val ft = adapter.fm.beginTransaction()
            var fragment = MapFragment()
            ft.replace(R.id.fragment_container, fragment)
            ft.addToBackStack("")
            ft.commit()
        }






    }
}