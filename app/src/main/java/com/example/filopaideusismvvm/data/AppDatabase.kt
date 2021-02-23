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

                sections.insert(SectionsData(1, 1, 1000, "Μαθηματικά", "Ενότητα 1", "Πληροφορίες:\n-Πρόσθεση\n-Αφαίρεση\n-Συνολικές Ερωτήσεις: 4\n-Διάρκεια: 4 λεπτά", true))
                sections.insert(SectionsData(2, 1, 1001, "Γλώσσα", null, null, true))
                sections.insert(
                    SectionsData(3, 1, 1002, "ΑΝΘΟΛΟΓΙΟ ΛΟΓΟΤΕΧΝΙΚΩΝ ΚΕΙΜΕΝΩΝ", null, null, true)
                )
                sections.insert(SectionsData(4, 1, 1003, "ΜΟΥΣΙΚΗ", null, null, true))
                sections.insert(SectionsData(5, 1, 1004, "ΜΕΛΕΤΗ ΠΕΡΙΒΑΛΛΟΝΤΟΣ", null, null, true))
                sections.insert(SectionsData(6, 2, 1005, "Γλώσσα", null, null, true))

                sections.insert(SectionsData(7, 6, 1100, "Ιστορία", null, null, true))
                sections.insert(SectionsData(8, 6, 1101, "Γλώσσα", null, null, true))

                question.insert(
                    QuestionData(1, 1000, "2 + 1 =", "1", "2", "3", "4", "3", null, checked1 = false, checked2 = false, checked3 = false, checked4 = false, "Μάλλον είναι 2 ή 3", null)
                )
                question.insert(
                    QuestionData(2, 1000, "1 + 3 =", "1", "2", "3", "4", "4", null, checked1 = false, checked2 = false, checked3 = false, checked4 = false, null, null)
                )
                question.insert(
                    QuestionData(3, 1000, "5 - 2 =", "1", "2", "3", "4", "3", null, checked1 = false, checked2 = false, checked3 = false, checked4 = false, null, null)
                )
                question.insert(
                    QuestionData(4, 1000, "9 - 7 =", "1", "2", "3", "4", "2", null, checked1 = false, checked2 = false, checked3 = false, checked4 = false, "Μάλλον είναι 2", null)
                )
                question.insert(
                    QuestionData(
                        5,
                        1101,
                        "Χάθηκαν τα βιβλία <i><strong>του Γιώργου</strong></i>",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        null,
                        "κτητική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        6,
                        1101,
                        "Μπήκαν στην αποθήκη <i><strong>των βιβλίων</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        null,
                        null,
                        "περιεχομένου",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        "περιεχομένου",
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        7,
                        1101,
                        "Καταστράφηκαν τα φτερά <i><strong>του αεροπλάνου</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        "αντικειμενική",
                        "κτητική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        8,
                        1101,
                        "Μου πήρε χρόνο το κόψιμο <i><strong>του χαρτιού</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        "αντικειμενική",
                        "αντικειμενική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        9,
                        1101,
                        "Παρακολουθούμε την επιστροφή <i><strong>των πουλιών</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        "αντικειμενική",
                        "υποκειμενική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        10,
                        1101,
                        "Στην α’ Γυμνασίου διδάσκεται η Οδύσσεια <i><strong>του Ομήρου</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "δημιουργού",
                        "σκοπού",
                        "δημιουργού",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        11,
                        1101,
                        "Χάθηκαν τα βιβλία <i><strong>του Γιώργου</strong></i>",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        null,
                        "κτητική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        12,
                        1101,
                        "Μπήκαν στην αποθήκη <i><strong>των βιβλίων</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        "αντικειμενική",
                        "περιεχομένου",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        13,
                        1101,
                        "Καταστράφηκαν τα φτερά <i><strong>του αεροπλάνου</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        null,
                        null,
                        "κτητική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        14,
                        1101,
                        "Μου πήρε χρόνο το κόψιμο <i><strong>του χαρτιού</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        "αντικειμενική",
                        "αντικειμενική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        15,
                        1101,
                        "Παρακολουθούμε την επιστροφή <i><strong>των πουλιών</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "υποκειμενική",
                        "αντικειμενική",
                        "υποκειμενική",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        16,
                        1101,
                        "Στην α’ Γυμνασίου διδάσκεται η Οδύσσεια <i><strong>του Ομήρου</strong></i>.",
                        "κτητική",
                        "περιεχομένου",
                        "δημιουργού",
                        null,
                        "δημιουργού",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        null,
                        null
                    )
                )
                question.insert(
                    QuestionData(
                        17,
                        1100,
                        "Η επανάσταση της 3ης Σεπτεμβρίου 1843 υπήρξε:",
                        "γέννημα της παρακμής των ξενικών κομμάτων",
                        "αποτέλεσμα της επέμβασης των ξένων δυνάμεων στην εσωτερική πολιτική του νεοσύστατου ελληνικού κράτους",
                        "καταλυτικός παράγοντας για τη διαμόρφωση των πολιτικών πραγμάτων",
                        "αποτέλεσμα της δυναμικής παρουσίας του ρωσικού κόμματος",
                        "καταλυτικός παράγοντας για τη διαμόρφωση των πολιτικών πραγμάτων",
                        null,
                        checked1 = false,
                        checked2 = false,
                        checked3 = false,
                        checked4 = false,
                        "Τον Οκτώβριο και τον Νοέμβριο έγιναν οι εκλογές του 1843 και οι εκλεγμένοι πληρεξούσιοι συγκρότησαν την συνταγματική Εθνική Συνέλευση που είχαν απαιτήσει όσοι έλαβαν μέρος στην επανάσταση της 3ης Σεπτεμβρίου και συνέταξαν Σύνταγμα, το οποίο υπέγραψε ο Όθωνας. Από τότε η πλατεία των Ανακτόρων μετονομάστηκε σε Πλατεία Συντάγματος.",
                        null
                    )
                )
            }
        }
    }
}