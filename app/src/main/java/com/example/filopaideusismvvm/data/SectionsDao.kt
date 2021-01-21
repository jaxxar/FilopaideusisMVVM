package com.example.filopaideusismvvm.data

import androidx.room.*
import com.example.filopaideusismvvm.utilities.TABLE_SECTIONS
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionsDao {

    @Query("SELECT * FROM $TABLE_SECTIONS")
    fun getAllSections(): Flow<List<SectionsData>>

    @Transaction
    @Query("SELECT * FROM $TABLE_SECTIONS WHERE student_class_id = :studentClassId ")
    fun getSections(studentClassId: Int): Flow<List<SectionsData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sectionsData: SectionsData)

}