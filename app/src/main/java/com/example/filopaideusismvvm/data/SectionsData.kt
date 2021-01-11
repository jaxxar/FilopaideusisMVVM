package com.example.filopaideusismvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SectionsData")
data class SectionsData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String? = null,
    val visibility: Boolean? = false
)
