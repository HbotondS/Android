package com.example.moviedb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ProfileFragment : MyFragment() {

    private val TAG = "ProfileFragment"

    private lateinit var myView: View

    override val type = ViewType.Profile

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.profile_layout, container, false)

        return myView
    }
}