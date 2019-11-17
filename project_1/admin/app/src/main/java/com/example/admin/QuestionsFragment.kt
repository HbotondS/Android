package com.example.admin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class QuestionsFragment : Fragment() {

    private val TAG = "QuestionsFragment"

    private lateinit var names: ArrayList<String>

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.questions_layout, container, false)
        myView.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.layoutHolder, NewQuestionFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        return myView
    }

    private fun getData() {
        // todo: get data
    }

    private fun initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list")
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = Adapter2Questions(myView.context, names)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(myView.context)
    }
}
