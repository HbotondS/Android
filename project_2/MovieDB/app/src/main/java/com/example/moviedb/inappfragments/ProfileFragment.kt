package com.example.moviedb.inappfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.moviedb.R
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProfileFragment : MyFragment() {

    private val TAG = "ProfileFragment"

    private lateinit var myView: View

    override val type = ViewType.Profile

    lateinit var user: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.profile_layout, container, false)

        user = activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("user", "")!!
        myView.findViewById<TextView>(R.id.user).text = "User: $user"

        myView.findViewById<Button>(R.id.changePwd).setOnClickListener {
            changePwdListener()
        }
        addTextListener(R.id.oldPwd, R.id.passwordLayout)
        addTextListener(R.id.newPwd, R.id.password2Layout)
        addTextListener(R.id.newPwd2, R.id.password3Layout)

        return myView
    }

    private fun addTextListener(textInput: Int, textInputLayout: Int) {
        myView.findViewById<TextInputEditText>(textInput).addTextChangedListener {
            var error = ""
            if (it.isNullOrBlank()) {
                error = "Cannot be empty"
            }
            myView.findViewById<TextInputLayout>(textInputLayout)
                ?.error = error
        }
    }

    private fun changePwdListener() {
        val oldPwd = view?.findViewById<TextInputEditText>(R.id.oldPwd)?.text.toString()
        val pwd1 = view?.findViewById<TextInputEditText>(R.id.newPwd)?.text.toString()
        val pwd2 = view?.findViewById<TextInputEditText>(R.id.newPwd2)?.text.toString()
        if (pwd1 != pwd2 && pwd1.isNotBlank() && pwd2.isNotBlank()) {

            Utils.makeToast(context!!, "The two password doesn't match")
        } else {
            changePassword(oldPwd, pwd1)
        }
    }

    private fun changePassword(oldPwd: String, pwd: String) {
        val userId = activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("userid", "")!!
        Constants.myRef4Users.child(userId).child("pwd")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val hashedPwd = Utils.md5(oldPwd)
                    if (dataSnapshot.value == hashedPwd) {
                        Log.d(TAG, "Changing password for $user")
                        Constants.myRef4Users.child(userId).child("pwd").setValue(hashedPwd)
                        Utils.makeToast(context!!, "Password changed successfully")
                    } else {
                        myView.findViewById<TextInputLayout>(R.id.passwordLayout)
                            ?.error = "Password is incorrect"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
    }
}