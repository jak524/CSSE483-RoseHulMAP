package edu.rosehulman.knuppja.rosehulmap

import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.building_row_view.view.*

class RoomViewHolder(itemView: View, var adapter: RoomListAdapter) : RecyclerView.ViewHolder(itemView), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    fun bind(room: Room) {
        itemView.building_name_text_view.text = room.name

        itemView.building_fact_text_view.text = room.type

        itemView.setOnClickListener {
            val ft = adapter.fm.beginTransaction()
            var fragment = MapFragment()
            fragment.getMapAsync(this)
            ft.replace(R.id.fragment_container, fragment)
            ft.addToBackStack("")
            ft.commit()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.isIndoorEnabled = true

        // Add a marker in Sydney and move the camera
        val olin = LatLng(39.4828715, -87.3249709)
        mMap.addMarker(MarkerOptions().position(olin).title("Olin"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(olin))
    }

}