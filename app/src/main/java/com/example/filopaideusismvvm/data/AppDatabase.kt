package com.example.filopaideusismvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.filopaideusismvvm.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [StudentClassData::class, SectionsData::class, QuestionData::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentClassDao(): StudentClassDao
    abstract fun sectionsDao(): SectionsDao
    abstract fun questionDao(): QuestionDao

    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val studentClassDao = database.get().studentClassDao()
            val sectionsDao = database.get().sectionsDao()
            val questionDao = database.get().questionDao()

            applicationScope.launch {
                studentClassDao.insert(StudentClassData(1, "Α Δημοτικού", true))
                studentClassDao.insert(StudentClassData(2, "Β Δημοτικού", true))
                studentClassDao.insert(StudentClassData(3, "Γ Δημοτικού", true))
                studentClassDao.insert(StudentClassData(4, "Δ Δημοτικού", true))
                studentClassDao.insert(StudentClassData(5, "Ε Δημοτικού", true))
                studentClassDao.insert(StudentClassData(6, "ΣΤ Δημοτικού", true))
            }
        }
    }
}