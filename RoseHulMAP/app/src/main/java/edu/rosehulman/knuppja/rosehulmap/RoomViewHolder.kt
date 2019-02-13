package edu.rosehulman.knuppja.rosehulmap

import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
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


        var coordinatesLat = adapter.rooms[adapterPosition].coords.latitude
        var coordinateLong = adapter.rooms[adapterPosition].coords.longitude
        var roomName = adapter.rooms[adapterPosition].name

//        39.482689, -87.324320
//        val coord =  LatLng(39.482689, -87.324320)
        val coord =  LatLng(coordinatesLat,coordinateLong)
        Log.d(Constants.TAG,"hello" + coord.toString())
        mMap.addMarker(MarkerOptions().position(coord).title(roomName))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord,19.0f))
    }

}