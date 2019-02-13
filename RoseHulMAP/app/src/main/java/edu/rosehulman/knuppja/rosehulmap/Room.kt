package edu.rosehulman.knuppja.rosehulmap

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp

data class Room(var name: String="", var type: String="", var building: String="", var coords: LatLng=LatLng(39.4828563, -87.3248556))  {

    @ServerTimestamp
    var timestamp: Timestamp? = null
    @get:Exclude
    var id = ""


    companion object {
        const val LAST_TOUCHED_KEY = "lastTouched"

        fun fromSnapshot(snapshot: DocumentSnapshot): Room {
            val room = snapshot.toObject(Room::class.java)!!

            room.id = snapshot.id

            return room
        }
    }
}