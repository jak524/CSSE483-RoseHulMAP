package edu.rosehulman.knuppja.rosehulmap

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp

data class Building(var name: String="", var funFact: String="")  {

    @ServerTimestamp
    var timestamp: Timestamp? = null
    @get:Exclude
    var id = ""


    companion object {
        const val LAST_TOUCHED_KEY = "lastTouched"

        fun fromSnapshot(snapshot: DocumentSnapshot): Building {
            val building = snapshot.toObject(Building::class.java)!!

            building.id = snapshot.id

            return building
        }
    }
}