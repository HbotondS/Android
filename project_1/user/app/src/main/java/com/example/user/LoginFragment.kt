package com.example.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

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
        myView = inflater.inflate(R.layout.join_session_layout, container, false)
        myView.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            val sessionName = myView.findViewById<EditText>(R.id.sessionTxt).text.toString()
            val userName = myView.findViewById<EditText>(R.id.nameTxt).text.toString()

            if (sessionName.isNotEmpty()) {
                if (!dbHelper.checkSession(sessionName)) {
                    // dbHelper.createSession(sessionName)
                    Utils.makeToast(myView.context, "Invalid session id")
                } else {

                    activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
                        ?.edit()
                        ?.putString("sessionName", sessionName)
                        ?.putString("user", userName)
                        ?.apply()
                    Utils.startFragment(fragmentManager, R.id.layoutHolder, VoteFragment())
                }
            } else {
                Utils.makeToast(myView.context, "Session field is empty")
            }
        }

        return myView
    }
}