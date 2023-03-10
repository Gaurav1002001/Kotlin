package com.example.kotlinconcepts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDB : RoomDatabase() {

    abstract fun studentDao() : StudentDao

    companion object {
        private var INSTANCE: StudentDB? = null

        fun getInstance(context: Context) : StudentDB {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDB::class.java,
                    "student_database"
                ).build()
            }

            return instance
        }
    }
}