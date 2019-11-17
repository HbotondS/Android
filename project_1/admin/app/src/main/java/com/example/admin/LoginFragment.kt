package com.example.admin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.login_layout, container, false)
        myView.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.layoutHolder, QuestionsFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        return myView
    }
}