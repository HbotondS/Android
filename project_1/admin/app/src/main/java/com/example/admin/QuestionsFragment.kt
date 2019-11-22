package com.example.admin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuestionsFragment : Fragment() {

    private val TAG = "QuestionsFragment"

    private var names = ArrayList<String>()

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.questions_layout, container, false)
        myView.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Utils.startFragment(fragmentManager, R.id.layoutHolder, NewQuestionFragment())
        }
        getData()

        return myView
    }

    private fun getData() {
        val sessionName = activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
            ?.getString("sessionName", "")

        val myRef = FirebaseDatabase.getInstance().getReference("sessions")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach { value ->
                    if (sessionName == value!!.child("sessionName").getValue(String::class.java)) {
                        Log.d(TAG, "Session name is: $sessionName")
                        val questions = value.child("questions")

                        names = ArrayList()
                        questions.children.forEach { question ->
                            val questionName = question.getValue(String::class.java)
                            Log.d(TAG, "Question name is: $questionName")
                            if (questionName != null) {
                                names.add(questionName)
                            }
                        }
                    }
                }
                myView.findViewById<TextView>(R.id.emptyList).text = if (names.isEmpty()) "No question found." else ""

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
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = Adapter2Questions(myView.context, names)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(myView.context)
    }
}
