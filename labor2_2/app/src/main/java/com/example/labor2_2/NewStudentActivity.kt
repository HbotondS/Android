package com.example.labor2_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class NewStudentActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        db = DatabaseHelper(this)
    }

    fun addStudent(view: View) {
        db.addStudent(findViewById<EditText>(R.id.fnameTxt).text.toString(),
            findViewById<EditText>(R.id.lnameTxt).text.toString(),
            findViewById<EditText>(R.id.courseTxt).text.toString())

        startActivity(Intent(this, MainActivity::class.java))
    }
}
