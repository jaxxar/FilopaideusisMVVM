package com.example.filopaideusismvvm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentClassDao {

    @Query("SELECT * FROM student_class")
    fun getStudentClass(): Flow<List<StudentClassData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studentClassData: StudentClassData)
}