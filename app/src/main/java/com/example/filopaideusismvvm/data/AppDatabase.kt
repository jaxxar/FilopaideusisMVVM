package com.example.filopaideusismvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StudentClassData::class, SectionsData::class, QuestionData::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentClassDao(): StudentClassDao
    abstract fun sectionsDao(): SectionsDao
    abstract fun questionDao(): QuestionDao


}