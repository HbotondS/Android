package com.example.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VotesFragment : Fragment() {

    private val TAG = "VotesFragment"

    private lateinit var names: ArrayList<String>
    private lateinit var votes: ArrayList<String>

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.votes_layout, container, false)
        getData()

        return myView
    }

    private fun getData() {
        val sessionName = activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("sessionName", "")
        val questionName = activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("questionName", "")

        myView.findViewById<TextView>(R.id.questionTitle).append(" $questionName")

        val myRef = FirebaseDatabase.getInstance().getReference("sessions")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach { value ->
                    if (value.key == sessionName) {
                        names = ArrayList()
                        votes = ArrayList()
                        value.child("questions").children.forEach { question ->
                            if (question.key == questionName) {
                                question.child("users").children.forEach{ user ->
                                    names.add(user.key.toString())
                                    votes.add(user.getValue(String::class.java)!!)
                                    Log.d(TAG, user.toString())
                                }
                            }
                        }
                        Log.d(TAG, "questions: $names")
                        Log.d(TAG, "votes: $votes")
                    }
                }
                initRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    private fun initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list")
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = Adapter2Votes(myView.context, names, votes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(myView.context)
    }
}