package com.example.kotlinconcepts.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "STUDENTS")
class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    val id: Int,

    @ColumnInfo(name = "student_name")
    val name: String,

    @ColumnInfo(name = "student_email")
    val email: String
)