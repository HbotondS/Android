package com.example.labor2_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var fnames: ArrayList<String>
    private lateinit var lnames: ArrayList<String>
    private lateinit var courses: ArrayList<String>
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)

        getDatas()
        initRecyclerView()
    }

    private fun getDatas() {
        fnames = db.getFirstNames()
        lnames = db.getLastNames()
        courses = db.getCourses()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewList)
        val adapter = RecyclerViewAdapter2List(this, fnames, lnames, courses)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun addStudent(view: View) {
        startActivity(Intent(this, NewStudentActivity::class.java))
    }
}
