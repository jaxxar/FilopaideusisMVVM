package com.example.filopaideusismvvm.data

import androidx.room.*
import com.example.filopaideusismvvm.utilities.TABLE_STUDENT_CLASS
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentClassDao {

    @Query("SELECT * FROM $TABLE_STUDENT_CLASS")
    fun getAllStudentClass(): Flow<List<StudentClassData>>

    @Transaction
    @Query("SELECT * FROM $TABLE_STUDENT_CLASS WHERE title LIKE '%' || :searchQuery || '%'")
    fun getStudentClass(searchQuery: String): Flow<List<StudentClassData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studentClassData: StudentClassData)
}