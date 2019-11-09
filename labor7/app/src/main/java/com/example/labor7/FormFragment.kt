package com.example.labor7

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class FormFragment : Fragment() {

    var TAG = "FormFragment"

    private var arraySpinner = arrayOf("Targu Mures", "Cluj Napoca", "Brasov")

    private lateinit var myView: View
    private lateinit var nameTxt: EditText
    private lateinit var locationTxt: Spinner
    private lateinit var profilePic: ImageView
    private lateinit var birthDateTxt: TextView
    private lateinit var hobbies: CheckBox
    private lateinit var gender: RadioGroup
    private lateinit var department: Spinner
    private lateinit var yearOfStudy: RadioGroup
    private lateinit var expectationTxt: EditText

    private lateinit var student: Student

    private lateinit var dataBaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")

        myView = inflater.inflate(R.layout.form_layout, container, false)
        getViewElements()
        addSaveButtonListener()

        birthDateTxt = myView.findViewById(R.id.dateTxt)
        val currentDate = SimpleDateFormat("yyyy. MM. dd.", Locale.getDefault()).format(Date())
        birthDateTxt.text = currentDate


        dataBaseRef = FirebaseDatabase.getInstance().reference.child("Student")

        return myView
    }

    private fun getViewElements() {
        nameTxt = myView.findViewById(R.id.nameTxt)
        locationTxt = myView.findViewById(R.id.locationSpinner)
        profilePic = myView.findViewById(R.id.profilePic)
        birthDateTxt = myView.findViewById(R.id.dateTxt)
        hobbies = myView.findViewById(R.id.hobbyCheckBox)
        gender = myView.findViewById(R.id.genderRadio)
        department = myView.findViewById(R.id.departmentSpinner)
        yearOfStudy = myView.findViewById(R.id.yearOfStudy)
        expectationTxt = myView.findViewById(R.id.expectationTxt)
    }

    private fun getStudent() {
        student = Student()
        student.name = nameTxt.text.toString()
        student.location = locationTxt.selectedItem.toString()
        // todo: store picture & upload to firebase
        student.birthDate = birthDateTxt.text.toString()
        student.hobby = hobbies.isChecked
        student.gender =
            gender.findViewById<RadioButton>(gender.checkedRadioButtonId).text.toString()
        student.department = department.selectedItem.toString()
        student.yearOfStudy =
            yearOfStudy.findViewById<RadioButton>(yearOfStudy.checkedRadioButtonId).text.toString()
                .toInt()
        student.expectation = expectationTxt.text.toString()
    }

    private fun addSaveButtonListener() {
        myView.findViewById<Button>(R.id.saveBtn).setOnClickListener {
            Toast.makeText(context, "Saving data", Toast.LENGTH_LONG).show()
            getStudent()

            fragmentManager?.beginTransaction()?.replace(R.id.layoutHolder, ListFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}