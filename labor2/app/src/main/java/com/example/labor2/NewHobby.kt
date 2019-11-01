package com.example.labor2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewHobby : AppCompatActivity() {

    val databaseHelper = DatabaseHelper(this)
    lateinit var hobbyText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_hobby)

        val sharedPref = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val name = sharedPref.getString(getString(R.string.name), null)

        hobbyText = findViewById(R.id.hobbyText)
    }

    fun viewHobbies(view: View) {
        val intent = Intent(this, HobbiesActivity::class.java)
        startActivity(intent)
    }

    fun addHobby(view: View) {
        if (hobbyText.text.isEmpty()) {
            hobbyText.error = "Field is empty"
        } else {
            databaseHelper.addHobby(hobbyText.text.toString())
        }
    }
}
