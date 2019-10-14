package com.example.labor2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hobbys")
data class Hobby(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "student_id")
        val student_id: String?,
        @ColumnInfo(name = "hobby")
        val hobby: String?
)