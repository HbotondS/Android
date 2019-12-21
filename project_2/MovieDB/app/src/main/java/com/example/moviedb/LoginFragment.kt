package com.example.moviedb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
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
        } else {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    dataSnapshot.children.forEach { value ->
                        if ((value.child("usr").value == "demo") and
                            (value.child("pwd").value == "demo")) {
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

}
