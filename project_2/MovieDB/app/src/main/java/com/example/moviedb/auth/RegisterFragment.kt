package com.example.moviedb.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {

    private val TAG = "RegisterFragment"

    private lateinit var myView: View

    private lateinit var myRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.sign_up_layout, container, false)

        myRef = FirebaseDatabase.getInstance().getReference("users")

        myView.findViewById<Button>(R.id.registerBtn).setOnClickListener {
            registerBtnListener()
        }

        return myView
    }

    private fun registerBtnListener() {
        // todo: implement storing data to the database
    }
}