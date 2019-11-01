package com.example.labor2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "School", null, 3) {

    private val TAG = "DatabaseHelper"
    private val STUDENT_CREATE = "CREATE TABLE student (TID integer primary key autoincrement, name text," +
            "email text, password text, birth_date text)"
    private val HOBBIES_CREATE = "CREATE TABLE hobbies (HID integer primary key autoincrement, hobby text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(STUDENT_CREATE)
        db?.execSQL(HOBBIES_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS student")
        db?.execSQL("DROP TABLE IF EXISTS hobbies")
        onCreate(db)
    }

    fun addStudent(name: String, email: String, password: String, birthDate: String): Boolean {
        Log.d(TAG, "Insert student")

        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("email", email)
        contentValues.put("password", password)
        contentValues.put("birth_date", birthDate)

        db.insert("student", null, contentValues)
        db.close()

        return true
    }

    fun addHobby(hobby: String): Boolean {
        Log.d(TAG, "Insert hobby")

        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("hobby", hobby)

        db.insert("hobbies", null, contentValues)
        db.close()

        return true
    }

    fun getHobbies(): List<String> {
        val hobbies = ArrayList<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT hobby FROM hobbies;", null)
        while (cursor.moveToNext()) {
            hobbies.add(cursor.getString(0))
            cursor.close()
        }

        return hobbies
    }

}