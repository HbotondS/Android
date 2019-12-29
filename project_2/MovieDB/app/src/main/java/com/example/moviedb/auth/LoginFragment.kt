package com.example.moviedb.auth

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.moviedb.NavigationFragment
import com.example.moviedb.R
import com.example.moviedb.inappfragments.HomeFragment
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

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
            loginBtnListener()
        }
        myView.findViewById<Button>(R.id.signUpBtn).setOnClickListener {
            Utils.startFragment(fragmentManager, R.id.layoutHolder, RegisterFragment())
        }
        addTextListener(R.id.usernameTxt, R.id.usernameLayout)
        addTextListener(R.id.passwordTxt, R.id.pwdLayout)

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

    private fun loginBtnListener() {
        val usr = view?.findViewById<TextInputEditText>(R.id.usernameTxt)?.text.toString()
        val pwd = view?.findViewById<TextInputEditText>(R.id.passwordTxt)?.text.toString()
        if (!isOnline(context!!) ) {
                Utils.makeSnackBar(
                    activity?.findViewById(R.id.layoutHolder)!!,
                    "You are offline"
                )
            }else if (usr.isNotBlank() && pwd.isNotBlank()) {
                login(usr, pwd)
            }
    }

    private fun login(usr: String, pwd: String) {
        Constants.myRef4Users.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach { value ->
                    if ((value.child("usr").value == usr) &&
                        (value.child("pwd").value == Utils.md5(pwd))
                    ) {
                        activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
                            ?.edit()
                            ?.putString("user", usr)
                            ?.putString("userid", value.key)
                            ?.apply()
                        Utils.startFragment(
                            fragmentManager,
                            R.id.layoutHolder, HomeFragment()
                        )
                        Utils.startFragment(
                            fragmentManager,
                            R.id.navigationBarHolder,
                            NavigationFragment()
                        )
                        return
                    }
                }
                Utils.makeToast(context!!, "Username or password is invalid")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
