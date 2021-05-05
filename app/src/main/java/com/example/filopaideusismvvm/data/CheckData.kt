package com.example.filopaideusismvvm.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filopaideusismvvm.utilities.CHECK_DATA
import kotlinx.parcelize.Parcelize

@Entity(tableName = CHECK_DATA)
@Parcelize
class CheckData(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "checked_1") var checked1: Boolean? = false,
        @ColumnInfo(name = "checked_2") var checked2: Boolean? = false,
        @ColumnInfo(name = "checked_3") var checked3: Boolean? = false,
        @ColumnInfo(name = "checked_4") var checked4: Boolean? = false,
) : Parcelable