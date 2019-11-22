package com.example.admin

import android.util.Log
import com.google.firebase.database.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


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
                    val sessionName = value!!.child("sessionName").getValue(String::class.java)
                    Log.d(TAG, "Session name is: $sessionName")
                    val questions = value.child("questions")
                    val questionList = ArrayList<String>()
                    questions.children.forEach { question ->
                        val questionName = question.getValue(String::class.java)
                        Log.d(TAG, "Question name is: $questionName")
                        if (questionName != null) {
                            questionList.add(questionName)
                        }
                    }
                    sessions[sessionName!!] = questionList
                    Log.d(TAG, "sessions is: $sessions")
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

    fun createSession(sessionName: String) {
        val myRefChild = myRef.push()
        myRefChild.child("sessionName").setValue(sessionName)
    }
}