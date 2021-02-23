package com.example.filopaideusismvvm.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filopaideusismvvm.utilities.TABLE_SECTIONS
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_SECTIONS)
@Parcelize
data class SectionsData(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo(name = "student_class_id") val studentClassId: Int = 0,
    @ColumnInfo(name = "list_questions_id") val listQuestionsId: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subTitle") val subTitle: String? = "",
    @ColumnInfo(name = "info") val info: String? = "",
    @ColumnInfo(name = "visibility") val visibility: Boolean? = false
) : Parcelable
