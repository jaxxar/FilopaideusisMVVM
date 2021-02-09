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

            val studentClass = database.get().studentClassDao()
            val sections = database.get().sectionsDao()
            val question = database.get().questionDao()

            applicationScope.launch {
                studentClass.insert(StudentClassData(1, "Α Δημοτικού", true))
                studentClass.insert(StudentClassData(2, "Β Δημοτικού", true))
                studentClass.insert(StudentClassData(3, "Γ Δημοτικού", true))
                studentClass.insert(StudentClassData(4, "Δ Δημοτικού", true))
                studentClass.insert(StudentClassData(5, "Ε Δημοτικού", true))
                studentClass.insert(StudentClassData(6, "ΣΤ Δημοτικού", true))

                sections.insert(SectionsData(1, 1, 1000, "Μαθηματικά", true))
                sections.insert(SectionsData(2, 1, 1001, "Γλώσσα", true))
                sections.insert(
                    SectionsData(3, 1, 1002, "ΑΝΘΟΛΟΓΙΟ ΛΟΓΟΤΕΧΝΙΚΩΝ ΚΕΙΜΕΝΩΝ", true)
                )
                sections.insert(SectionsData(4, 1, 1003, "ΜΟΥΣΙΚΗ", true))
                sections.insert(SectionsData(5, 1, 1004, "ΜΕΛΕΤΗ ΠΕΡΙΒΑΛΛΟΝΤΟΣ", true))
                sections.insert(SectionsData(6, 2, 1001, "Γλώσσα", true))

                question.insert(
                    QuestionData(1, 1000, "2 + 1 =", "1", "2", "3", "4", "3", null, checked1 = false, checked2 = false, checked3 = false, checked4 = false, null, null)
                )
                question.insert(
                    QuestionData(2, 1000, "1 + 3 =", "1", "2", "3", "4", "4", null, checked1 = false, checked2 = false, checked3 = false, checked4 = false, null, null)
                )
            }
        }
    }
}