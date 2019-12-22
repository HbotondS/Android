package com.example.moviedb.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar

class Utils {

    companion object {
        var MY_PREFS_NAME = "MOVIE_DB"

        fun makeToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun startFragment(fragmentManager: FragmentManager?, layoutId: Int, fragment: Fragment) {
            fragmentManager?.beginTransaction()
                ?.replace(layoutId, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        fun makeSnackBar(view: View, msg: String) {
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
        }
    }
}