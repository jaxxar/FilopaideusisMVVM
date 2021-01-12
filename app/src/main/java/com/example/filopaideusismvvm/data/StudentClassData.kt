package com.example.filopaideusismvvm.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Student_Class")
@Parcelize
data class StudentClassData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String? = null,
    val visibility: Boolean? = false
) : Parcelable
