package com.example.labor2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HobbiesActivity : AppCompatActivity() {

    private val TAG = "HobbiesActivity"
    private lateinit var hobbies: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobbies)

        getDatas()
        initRecyclerView()
    }

    private fun getDatas() {
        hobbies = DatabaseHelper(this).getHobbies()
        Log.d(TAG, hobbies.size.toString())
    }

    private fun initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewList)
        val adapter = RecyclerViewAdapter2List(this, hobbies)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
