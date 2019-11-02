package com.example.labor2

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var nameText: EditText
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var birthDayText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        datePickerDialog = DatePickerDialog(this, this, year, month, day)

        databaseHelper = DatabaseHelper(this)

        nameText = findViewById(R.id.nameText)
        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)
        birthDayText = findViewById(R.id.birthDayText)
    }

    fun datePick(view: View) {
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        birthDayText.text = "$year. $month. $dayOfMonth."
    }

    fun login(view: View) {
        val sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        sharedPreferences.edit()
                .putString(getString(R.string.name), nameText.text.toString())
                .putString(getString(R.string.email), emailText.text.toString())
                .putString(getString(R.string.password), passwordText.text.toString())
                .putString(getString(R.string.birthDate), birthDayText.text.toString())
                .apply()

        databaseHelper.addStudent(nameText.text.toString(),
                emailText.text.toString(),
                passwordText.text.toString(),
                birthDayText.text.toString())

        val newHobby = Intent(this, NewHobby::class.java)
        startActivity(newHobby)
    }

}