package com.example.admin

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class FireBaseHelper {
    private var TAG = "FireBaseHelper"

    private var sessionNames: Vector<String>

    constructor() {
        sessionNames = Vector()
        val myRef = FirebaseDatabase.getInstance().getReference("sessions")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach { value ->
                    val sessionName = value!!.child("sessionName").getValue(String::class.java)
                    Log.d(TAG, "Value is: $sessionNames")
                    sessionNames.add(sessionName)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun checkSession(sessionName: String): Boolean {
        var sessionNameFound = false
        sessionNames.forEach { name ->
            if (sessionName == name) {
                sessionNameFound = true
            }
        }

        return sessionNameFound
    }
}