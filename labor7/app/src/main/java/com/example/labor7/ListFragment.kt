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

class ListFragment : Fragment() {

    private val TAG = "ListFragment"

    private lateinit var myView: View
    private lateinit var names: ArrayList<String>
    private lateinit var dates: ArrayList<String>

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

        getData()
        initRecyclerView()

        return myView
    }

    private fun getData() {
        names = ArrayList()
        names.add("Boti")

        dates = ArrayList()
        dates.add("2019.11.06")
    }

    private fun initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list")
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = RecyclerViewAdapter2List(myView.context, names, dates)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(myView.context)
    }
}