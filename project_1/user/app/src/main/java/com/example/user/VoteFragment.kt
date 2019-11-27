package com.example.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class VoteFragment : Fragment() {

    private val TAG = "VoteFragment"
    private val names = Vector(
        listOf("0", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "coffee")
    )
    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.vote_layout, container, false)

        getData()

        return myView
    }

    private fun getData() {
        val sessionName = activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("sessionName", "")

        val myRef = FirebaseDatabase.getInstance().getReference("sessions")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach { value ->
                    if (value.key == sessionName) {
                        value.child("questions").children.forEach { question ->
                            if (question.getValue(Boolean::class.java)!!) {
                                Log.w(TAG, "Active question found.")
                                myView.findViewById<TextView>(R.id.questionText).text = "Question: ${question.key.toString()}"
                            }
                        }
                    }
                }
                myView.findViewById<TextView>(R.id.sessionTitle).text =  "Session: $sessionName"
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
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerViewGrid)
        val adapter = Adapter2Grid(myView.context, names, listener = {
            Toast.makeText(myView.context, "clicked", Toast.LENGTH_LONG).show()
        })
        val layoutManager = GridLayoutManager(myView.context, 4)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}