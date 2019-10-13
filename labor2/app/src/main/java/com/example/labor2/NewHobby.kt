package com.example.labor2

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NewHobby : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_hobby)

        val sharedPref = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val name = sharedPref.getString(getString(R.string.name), null)
    }
}
