package com.example.moviedb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.utils.Utils
import com.example.moviedb.inappfragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationFragment : Fragment() {

    private val TAG = "NavigationFragment"

    private lateinit var myView: View

    private val inCinemaFragment = InCinemaFragment()
    private val favoritesFragment = FavoritesFragment()
    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()

    private var currentView: ViewType = ViewType.Home

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
                        changeView(inCinemaFragment)
                    }
                    R.id.favorites -> {
                        changeView(favoritesFragment)
                    }
                    R.id.home -> {
                        changeView(homeFragment)
                    }
                    R.id.profile -> {
                        changeView(profileFragment)
                    }
                }
                true
            }

        return myView
    }

    private fun changeView(newView: MyFragment) {
        if (this.currentView != newView.type) {
            this.currentView = newView.type
            Utils.startFragment(fragmentManager, R.id.layoutHolder, newView)
        }
    }

}
