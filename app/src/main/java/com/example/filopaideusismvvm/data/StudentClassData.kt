package com.example.filopaideusismvvm.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filopaideusismvvm.utilities.TABLE_STUDENT_CLASS
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_STUDENT_CLASS)
@Parcelize
data class StudentClassData(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "visibility") val visibility: Boolean? = false
) : Parcelable
