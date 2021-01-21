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
    version = 1,
    exportSchema = false
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

                sectionsDao.insert(SectionsData(1, 1, 1000, "Μαθηματικά", true))
                sectionsDao.insert(SectionsData(2, 1, 1001, "Γλώσσα", true))
                sectionsDao.insert(
                    SectionsData(
                        3,
                        1,
                        1002,
                        "ΑΝΘΟΛΟΓΙΟ ΛΟΓΟΤΕΧΝΙΚΩΝ ΚΕΙΜΕΝΩΝ",
                        true
                    )
                )
                sectionsDao.insert(SectionsData(4, 1, 1003, "ΜΟΥΣΙΚΗ", true))
                sectionsDao.insert(SectionsData(5, 1, 1004, "ΜΕΛΕΤΗ ΠΕΡΙΒΑΛΛΟΝΤΟΣ", true))
                sectionsDao.insert(SectionsData(6, 2, 1001, "Γλώσσα", true))

                questionDao.insert(
                    QuestionData(
                        1,
                        1000,
                        "2 + 1 =",
                        "1",
                        "2",
                        "3",
                        "4",
                        "3",
                        null,
                        null,
                        null
                    )
                )
                questionDao.insert(
                    QuestionData(
                        2,
                        1000,
                        "1 + 3 =",
                        "1",
                        "2",
                        "3",
                        "4",
                        "4",
                        null,
                        null,
                        null
                    )
                )
            }
        }
    }
}