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

    fun getFirstNames(): ArrayList<String> {
        Log.d(TAG, "get first names")

        val fnames = ArrayList<String>()
        val db = readableDatabase
        val cursor =  db.rawQuery("SELECT fname FROM student;", null)
        while (cursor.moveToNext()) {
            fnames.add(cursor.getString(0))
        }
        cursor.close()

        return fnames
    }

    fun getLastNames(): ArrayList<String> {
        Log.d(TAG, "get first names")

        val lnames = ArrayList<String>()
        val db = readableDatabase
        val cursor =  db.rawQuery("SELECT lname FROM student;", null)
        while (cursor.moveToNext()) {
            lnames.add(cursor.getString(0))
        }
        cursor.close()

        return lnames
    }

    fun getCourses(): ArrayList<String> {
        Log.d(TAG, "get first names")

        val courses = ArrayList<String>()
        val db = readableDatabase
        val cursor =  db.rawQuery("SELECT course FROM student;", null)
        while (cursor.moveToNext()) {
            courses.add(cursor.getString(0))
        }
        cursor.close()

        return courses
    }
}