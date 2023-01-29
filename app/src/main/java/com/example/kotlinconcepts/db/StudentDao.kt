package com.example.kotlinconcepts.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Update
    suspend fun update(student: Student)

    @Query("SELECT * FROM STUDENTS")
    fun getAllStudents(): LiveData<List<Student>>
}