package com.example.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        initRecyclerView()

        return myView
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