package edu.rosehulman.knuppja.rosehulmap

import android.app.PendingIntent.getActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.building_row_view.view.*

class ProfessorViewHolder(itemView: View, var adapter: ProfessorListAdapter) : RecyclerView.ViewHolder(itemView) {


    fun bind(prof: Professor) {
        itemView.building_name_text_view.text = prof.name

        itemView.building_fact_text_view.text = prof.building

        itemView.setOnClickListener {
            val ft = adapter.fm.beginTransaction()
            var fragment = BuildingFragment(adapter, adapterPosition)
            ft.replace(R.id.fragment_container, fragment)
            ft.addToBackStack("")
            ft.commit()
        }

//        itemView.setOnLongClickListener {
//            val fragment = PicFragment(adapter, adapterPosition)
//            val ft = adapter.fm.beginTransaction()
//            ft.replace(R.id.fragment_container, fragment)
//            ft.addToBackStack("")
//            ft.commit()
//
//            true
//        }




    }
}