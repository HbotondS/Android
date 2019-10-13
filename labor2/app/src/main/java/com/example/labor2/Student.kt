package com.example.labor2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "name")
        val name: String?,
        @ColumnInfo(name = "email")
        val email: String?,
        @ColumnInfo(name = "password")
        val password: String?,
        @ColumnInfo(name = "birthdate")
        val birthdate: String?
)