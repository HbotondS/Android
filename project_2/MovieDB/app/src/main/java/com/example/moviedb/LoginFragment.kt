package com.example.moviedb

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.moviedb.utils.Utils
import com.example.moviedb.inappfragments.HomeFragment
import com.google.firebase.database.*

class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"

    private lateinit var myView: View

    private lateinit var myRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.login_layout, container, false)

        myRef = FirebaseDatabase.getInstance().getReference("users")

        myView.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            loginBtnListener()
        }

        return myView
    }

    private fun loginBtnListener() {
        val usr = view?.findViewById<EditText>(R.id.usernameTxt)?.text.toString()
        val pwd = view?.findViewById<EditText>(R.id.passwordTxt)?.text.toString()
        if (usr.isEmpty() or pwd.isEmpty()) {
            Utils.makeSnackBar(
                activity?.findViewById(R.id.layoutHolder)!!,
                "Fields cannot be empty"
            )
        } else if(!isOnline(context!!)) {
            Utils.makeSnackBar(
                activity?.findViewById(R.id.layoutHolder)!!,
                "You are offline"
            )
        } else {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    dataSnapshot.children.forEach { value ->
                        if ((value.child("usr").value == usr) &&
                            (value.child("pwd").value == pwd)) {
                            Utils.startFragment(fragmentManager, R.id.layoutHolder, HomeFragment())
                            Utils.startFragment(fragmentManager, R.id.navigationBarHolder, NavigationFragment())
                            return
                        }
                    }
                    Utils.makeSnackBar(
                        activity?.findViewById(R.id.layoutHolder)!!,
                        "Username or password is invalid"
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
