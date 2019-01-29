package edu.rosehulman.knuppja.rosehulmap

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp

data class Professor(var name: String="", var bio: String="", var officeNumber: String="", var building: String="")  {

    @ServerTimestamp
    var timestamp: Timestamp? = null
    @get:Exclude
    var id = ""


    companion object {
        const val LAST_TOUCHED_KEY = "lastTouched"

        fun fromSnapshot(snapshot: DocumentSnapshot): Professor {
            val professor = snapshot.toObject(Professor::class.java)!!

            professor.id = snapshot.id

            return professor
        }
    }
}