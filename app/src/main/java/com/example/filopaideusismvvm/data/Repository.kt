package com.example.filopaideusismvvm.data

import androidx.room.withTransaction
import com.example.filopaideusismvvm.utilities.networkBoundResource
import com.google.firebase.database.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repository @Inject constructor(
    private val dp: AppDatabase
) {
    private lateinit var mDatabase: DatabaseReference
    private val questionDao = dp.questionDao()
    private val sectionsDao = dp.sectionsDao()
    private val studentClassDao = dp.studentClassDao()

    fun updateQuestions() = networkBoundResource(
        query = {
            questionDao.getAllQuestion()
        },
        fetch = {
            delay(2000)
            getQuestionData()
        },
        saveFetchResult = { questions ->
            dp.withTransaction {
                questionDao.deleteAllQuestions()
                questionDao.insertList(questions)
            }
        }
    )

    fun updateSections() = networkBoundResource(
        query = {
            sectionsDao.getAllSections()
        },
        fetch = {
            delay(2000)
            getSectionsData()
        },
        saveFetchResult = { sections ->
            dp.withTransaction {
                sectionsDao.deleteAllSections()
                sectionsDao.insertList(sections)
            }
        }
    )

    fun updateStudentClass() = networkBoundResource(
        query = {
            studentClassDao.getAllStudentClass()
        },
        fetch = {
            getStudentClassData()
        },
        saveFetchResult = { studentClass ->
            dp.withTransaction {
                studentClassDao.deleteAllStudentClass()
                while (studentClass.size == 0) {
                    studentClassDao.insertList(studentClass)
                }
            }
        }
    )

    private suspend fun getStudentClassData(): MutableList<StudentClassData> {
        val studentClassList = mutableListOf<StudentClassData>()
        mDatabase = FirebaseDatabase.getInstance().getReference("/StudentClass/")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val totalData = snapshot.childrenCount.toInt()
                for (currentItem in 0 until totalData) {
                    mDatabase = FirebaseDatabase.getInstance()
                        .getReference("/StudentClass/$currentItem")
                    mDatabase.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val studentClassData = StudentClassData(
                                id = snapshot.child("id").value.toString().toInt(),
                                title = snapshot.child("title").value.toString(),
                                visibility = snapshot.child("visibility").value as Boolean?,
                            )
                            studentClassList.add(studentClassData)
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return studentClassList
    }

    private fun getSectionsData(): MutableList<SectionsData> {
        val sectionsList = mutableListOf<SectionsData>()
        mDatabase = FirebaseDatabase.getInstance().getReference("/Sections/")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val totalData = snapshot.childrenCount.toInt()
                for (currentItem in 0 until totalData) {
                    mDatabase = FirebaseDatabase.getInstance()
                        .getReference("/Sections/$currentItem")
                    mDatabase.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val sectionsData = SectionsData(
                                id = snapshot.child("id").value.toString().toInt(),
                                studentClassId = snapshot.child("studentClassId").value.toString().toInt(),
                                listQuestionsId = snapshot.child("listQuestionsId").value.toString().toInt(),
                                title = snapshot.child("title").value.toString(),
                                subTitle = snapshot.child("subTitle").value.toString(),
                                info = snapshot.child("info").value.toString(),
                                visibility = snapshot.child("visibility").value as Boolean?,
                            )
                            sectionsList.add(sectionsData)
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return sectionsList
    }

    private fun getQuestionData(): MutableList<QuestionData> {
        val questionList = mutableListOf<QuestionData>()
        mDatabase = FirebaseDatabase.getInstance().getReference("/Questions/")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val totalData = snapshot.childrenCount.toInt()
                for (currentItem in 0 until totalData) {
                    mDatabase = FirebaseDatabase.getInstance()
                        .getReference("/Questions/$currentItem")
                    mDatabase.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val questionData = QuestionData(
                                id = snapshot.child("id").value as Int,
                                listQuestionsId = snapshot.child("listQuestionsId").value.toString().toInt(),
                                question = snapshot.child("question").value.toString(),
                                answer1 = snapshot.child("answer1").value.toString(),
                                answer2 = snapshot.child("answer2").value.toString(),
                                answer3 = snapshot.child("answer3").value.toString(),
                                answer4 = snapshot.child("answer4").value.toString(),
                                correctAnswer = snapshot.child("correctAnswer").value.toString(),
                                submittedAnswer = null,
                                hint = snapshot.child("hint").value.toString(),
                                chapter = snapshot.child("chapter").value.toString(),
                                questionType = snapshot.child("questionType").value.toString().toInt(),
                            )
                            questionList.add(questionData)
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return questionList
    }
}