package com.example.labor7

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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


class FormFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    var TAG = "FormFragment"

    private lateinit var datePickerDialog: DatePickerDialog

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
        addListeners()

        initDatePicker()

        birthDateTxt = myView.findViewById(R.id.dateTxt)
        val currentDate = SimpleDateFormat("yyyy. MM. dd.", Locale.getDefault()).format(Date())
        birthDateTxt.text = currentDate


        dataBaseRef = FirebaseDatabase.getInstance().reference.child("Student")

        return myView
    }

    private fun initDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        datePickerDialog = DatePickerDialog(context!!, this, year, month, day)
    }

    private fun addListeners() {
        addSaveButtonListener()
        addDatePickBtnListener()
        addChoosePicListener()
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

    private fun addDatePickBtnListener() {
        myView.findViewById<Button>(R.id.datePickBtn).setOnClickListener {
            datePickerDialog.show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        birthDateTxt.text = "$year. $month. $dayOfMonth."
    }

    private fun addChoosePicListener() {
        myView.findViewById<Button>(R.id.choosePic).setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(context?.packageManager!!) != null) {
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage = data.data
            val bitmapImage = ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    activity?.contentResolver!!,
                    selectedImage!!
                )
            )
            profilePic.setImageBitmap(bitmapImage)
        }
    }

}