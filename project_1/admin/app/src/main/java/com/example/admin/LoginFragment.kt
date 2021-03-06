package com.example.admin

import android.content.Context.MODE_PRIVATE
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
        myView = inflater.inflate(R.layout.login_layout, container, false)
        myView.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            val sessionName = myView.findViewById<EditText>(R.id.sessionTxt).text.toString()

            if (sessionName.isNotEmpty()) {
                if (!dbHelper.checkSession(sessionName)) {
                    dbHelper.createSession(sessionName)
                }

                activity?.getSharedPreferences(Utils.MY_PREFS_NAME, MODE_PRIVATE)
                    ?.edit()
                    ?.putString("sessionName", sessionName)
                    ?.apply()
                Utils.startFragment(fragmentManager, R.id.layoutHolder, QuestionsFragment())
            } else {
                Utils.makeToast(myView.context, "Session field is empty")
            }
        }

        return myView
    }
}