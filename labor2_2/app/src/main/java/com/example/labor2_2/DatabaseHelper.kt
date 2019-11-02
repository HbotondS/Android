package com.example.labor2_2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "School", null, 3) {

    private val TAG = "DatabaseHelper"
    private val STUDENT_CREATE = "CREATE TABLE student (TID integer primary key autoincrement, fname text," +
            "lname text, course text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(STUDENT_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS student")
        onCreate(db)
    }

    fun addStudent(fname: String, lname: String, course: String): Boolean {
        Log.d(TAG, "Insert student")

        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("fname", fname)
        contentValues.put("lname", lname)
        contentValues.put("course", course)

        db.insert("student", null, contentValues)
        db.close()

        return true
    }
}