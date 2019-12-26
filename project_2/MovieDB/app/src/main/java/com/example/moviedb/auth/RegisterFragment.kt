package com.example.moviedb.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class RegisterFragment : Fragment() {

    private val TAG = "RegisterFragment"

    private lateinit var myView: View
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var password2: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.sign_up_layout, container, false)


        username = myView.findViewById(R.id.usernameTxt)
        password = myView.findViewById(R.id.passwordTxt)
        password2 = myView.findViewById(R.id.passwordTxt2)

        myView.findViewById<Button>(R.id.registerBtn).setOnClickListener {
            registerBtnListener()
        }

        return myView
    }

    private fun registerBtnListener() {
        val usernameTxt = username.text.toString()
        val passwordTxt = password.text.toString()
        val password2Txt = password2.text.toString()
        when {
            usernameTxt.isEmpty() -> {
                myView.findViewById<TextInputLayout>(R.id.usernameLayout).error = "Cannot be empty"
            }
            passwordTxt.isEmpty() -> {
                myView.findViewById<TextInputLayout>(R.id.pwdLayout).error = "Cannot be empty"
            }
            password2Txt.isEmpty() -> {
                myView.findViewById<TextInputLayout>(R.id.pwdLayout2).error = "Cannot be empty"
            }
            passwordTxt != password2Txt -> {
                Utils.makeToast(context!!, "Passwords are not equal")
            }
            else -> {
                register(usernameTxt, passwordTxt)
            }
        }

    }

    private fun register(usernameTxt: String, passwordTxt: String) {
        Constants.myRef4Users.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var isExists = false
                dataSnapshot.children.forEach { value ->
                    if ((value.child("usr").value == usernameTxt)) {
                        Utils.makeToast(context!!, "Username already exists")
                        isExists = true
                    }
                }
                if (!isExists) {
                    val newUser = Constants.myRef4Users.push()
                    newUser.child("usr").setValue(usernameTxt)
                    newUser.child("pwd").setValue(Utils.md5(passwordTxt))
                    Utils.startFragment(fragmentManager, R.id.layoutHolder, LoginFragment())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
}