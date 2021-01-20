package com.example.filopaideusismvvm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filopaideusismvvm.utilities.TABLE_SECTIONS
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionsDao {

    @Query("SELECT * FROM $TABLE_SECTIONS")
    fun getSections(): Flow<List<SectionsData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sectionsData: SectionsData)

}