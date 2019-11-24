package com.example.admin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class NewQuestionFragment : Fragment() {
    private val TAG = "QuestionsFragment"

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.new_question_layout, container, false)
        myView.findViewById<Button>(R.id.addQuestionBtn).setOnClickListener {
            val questionName = myView.findViewById<EditText>(R.id.questionTxt).text.toString()
            if (questionName.isNotEmpty()) {
                val sessionName = activity?.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
                    ?.getString("sessionName", "").toString()
                FireBaseHelper().createQuestion(sessionName, myView.findViewById<EditText>(R.id.questionTxt).text.toString())

                fragmentManager?.popBackStackImmediate()
            } else {
                Utils.makeToast(myView.context, "Question name field is empty")
            }
        }

        return myView
    }
}
