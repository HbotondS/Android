package com.example.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class VoteFragment : Fragment() {

    private val TAG = "VoteFragment"
    private val names = Vector(
        listOf("0", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "coffee")
    )
    private var vote = ""
    private lateinit var sessionName: String
    private var questionName = ""
    private lateinit var userName: String
    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        sessionName = activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("sessionName", "")!!
        userName = activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("user", "")!!

        myView = inflater.inflate(R.layout.vote_layout, container, false)
        myView.findViewById<Button>(R.id.voteBtn).setOnClickListener {
            if (vote.isNotEmpty()) {
                FireBaseHelper().voteForQuestion(sessionName, questionName, userName, vote)
                activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
                    ?.edit()
                    ?.putString("questionName", questionName)
                    ?.apply()
                Utils.startFragment(fragmentManager, R.id.layoutHolder, VotesFragment())
            } else {
                Utils.makeToast(myView.context, "You didn't vote yet")
            }
        }

        getData()

        return myView
    }

    private fun getData() {
        val myRef = FirebaseDatabase.getInstance().getReference("sessions")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach { value ->
                    if (value.key == sessionName) {
                        value.child("questions").children.forEach { question ->
                            if (question.child("isActive").getValue(Boolean::class.java)!!) {
                                Log.w(TAG, "Active question found.")
                                questionName = question.key.toString()
                                myView.findViewById<TextView>(R.id.questionText).text =
                                    "Question: $questionName"
                            }
                        }
                        if (questionName.isEmpty()) {
                            Utils.makeToast(myView.context, "There is no active question")
                            Utils.startFragment(fragmentManager, R.id.layoutHolder, LoginFragment())
                        }
                    }
                }
                myView.findViewById<TextView>(R.id.sessionTitle).text = "Session: $sessionName"
                initRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    private fun initRecyclerView() {
        Log.d(TAG, "Init RecyclerView list")
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerViewGrid)
        val adapter = Adapter2Grid(myView.context, names, listener = {
            vote = names[it]
            Utils.makeToast(myView.context, "Your vote: $vote")
        })
        val layoutManager = GridLayoutManager(myView.context, 4)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}