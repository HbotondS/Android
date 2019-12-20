package com.example.moviedb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationFragment : Fragment() {

    private val TAG = "NavigationFragment"

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.navigation_bar, container, false)

        myView.findViewById<BottomNavigationView>(R.id.navigationBar).selectedItemId = R.id.home

        myView.findViewById<BottomNavigationView>(R.id.navigationBar)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.inCinema -> {
                        // todo
                    }
                    R.id.favorites -> {
                        // todo
                    }
                    R.id.home -> {
                        // todo
                    }
                    R.id.profile -> {
                        // todo
                    }
                }
                true
            }

        return myView
    }

}
