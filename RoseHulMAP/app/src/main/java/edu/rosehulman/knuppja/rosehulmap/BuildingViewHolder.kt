package edu.rosehulman.knuppja.rosehulmap

import android.app.PendingIntent.getActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.building_row_view.view.*

class BuildingViewHolder(itemView: View, var adapter: BuildingListAdapter) : RecyclerView.ViewHolder(itemView) {


    fun bind(building: Building) {
        itemView.building_name_text_view.text = building.name

        itemView.building_fact_text_view.text = building.funFact

        itemView.setOnClickListener {
            val ft = adapter.fm.beginTransaction()
            var fragment = BuildingFragment(adapter, adapterPosition)
            ft.replace(R.id.fragment_container, fragment)
            ft.addToBackStack("")
            ft.commit()
        }

        itemView.setOnLongClickListener {
            val fragment = RoomListFragment(adapter.fm)
            val ft = adapter.fm.beginTransaction()
            ft.replace(R.id.fragment_container, fragment)
            ft.addToBackStack("")
            ft.commit()

            true
        }




    }
}