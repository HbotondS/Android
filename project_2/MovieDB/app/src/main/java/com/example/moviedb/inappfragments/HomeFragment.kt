package com.example.moviedb.inappfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R

class HomeFragment : MyFragment() {

    private val TAG = "HomeFragment"

    override val type = ViewType.Home

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.home_layout, container, false)

        return myView
    }
}
