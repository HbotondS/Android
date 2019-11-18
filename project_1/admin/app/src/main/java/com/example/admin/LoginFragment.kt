package com.example.admin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"

    private lateinit var myView: View
    private var dbHelper = FireBaseHelper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.login_layout, container, false)
        myView.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            if (dbHelper.checkSession(myView.findViewById<EditText>(R.id.sessionTxt).text.toString())) {
                fragmentManager?.beginTransaction()?.replace(R.id.layoutHolder, QuestionsFragment())
                    ?.addToBackStack(null)
                    ?.commit()
            } else {
                Snackbar.make(myView, "Session not found", Snackbar.LENGTH_LONG).show()
            }
        }

        return myView
    }
}