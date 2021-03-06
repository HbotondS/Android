package com.example.labor7

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class ListFragment : Fragment() {

    private val TAG = "ListFragment"

    private lateinit var myView: View
    private lateinit var names: ArrayList<String>
    private lateinit var dates: ArrayList<String>

    private lateinit var dataBaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.recycleview_layout, container, false)

        myView.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.layoutHolder, FormFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        dataBaseRef = FirebaseDatabase.getInstance().reference.child("Student")

        names = ArrayList()
        dates = ArrayList()
        getData()

        return myView
    }

    private fun getData() {
        dataBaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "Get data")
                for (messageSnapshot in dataSnapshot.children) {
                    val value = messageSnapshot.getValue(Student::class.java)
                    names.add(value?.name!!)
                    dates.add(value.birthDate)
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
        val adapter = RecyclerViewAdapter2List(myView.context, names, dates)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(myView.context)
    }
}