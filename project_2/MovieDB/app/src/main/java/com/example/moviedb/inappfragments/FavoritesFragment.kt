package com.example.moviedb.inappfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R

class FavoritesFragment : MyFragment() {

    private val TAG = "FavoritesFragment"

    override val type = ViewType.Favorites

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.favorites_layout, container, false)

        return myView
    }
}
