package edu.rosehulman.knuppja.rosehulmap

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class MapFragment() : SupportMapFragment(), OnMapReadyCallback {



    private lateinit var mMap: GoogleMap




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.isIndoorEnabled = true

        val line = mMap.addPolyline(
                PolylineOptions()
                        .add(LatLng(39.4828563, -87.3248556), LatLng(39.4825372, -87.3247302))
                        .width(5f)
                        .color(Color.RED)
        )

        // Add a marker in Sydney and move the camera
        val olin = LatLng(39.4828715, -87.3249709)
        mMap.addMarker(MarkerOptions().position(olin).title("Olin"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(olin))




    }


}
