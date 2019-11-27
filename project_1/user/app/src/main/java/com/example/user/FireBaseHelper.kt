package com.example.user

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FireBaseHelper {
    private var TAG = "FireBaseHelper"

    private var sessions = HashMap<String, ArrayList<String>>()

    private var myRef = FirebaseDatabase.getInstance().getReference("sessions")


    init {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach { value ->
                    Log.d(TAG, "session is: ${value.key}")
                    val questionList = ArrayList<String>()
                    value.child("questions").children.forEach { question ->
                        questionList.add(question.key.toString())
                    }
                    sessions[value.key.toString()] = questionList
                    Log.d(TAG, "questions: ${sessions[value.key]}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun checkSession(sessionName: String): Boolean {
        return sessions.containsKey(sessionName)
    }
}