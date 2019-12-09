package com.example.moviedb

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Utils {

    companion object {
        var MY_PREFS_NAME = "admin_app"

        fun makeToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun startFragment(fragmentManager: FragmentManager?, layoutId: Int, fragment: Fragment) {
            fragmentManager?.beginTransaction()
                ?.replace(layoutId, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}