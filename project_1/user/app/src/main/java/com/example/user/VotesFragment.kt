package com.example.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        return myView
    }

    private fun getData() {
        // todo: get data
    }

    private fun initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list")
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = Adapter2Votes(myView.context, names, votes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(myView.context)
    }
}