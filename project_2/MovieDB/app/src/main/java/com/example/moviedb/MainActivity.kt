package com.example.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.auth.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.layoutHolder, LoginFragment())
            .commit()
    }
}
