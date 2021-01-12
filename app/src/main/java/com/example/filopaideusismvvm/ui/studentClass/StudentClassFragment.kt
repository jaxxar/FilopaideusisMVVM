package com.example.filopaideusismvvm.ui.studentClass

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.viewmodels.StudentClassViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentClassFragment : Fragment(R.layout.fragment_student_class) {

    private val viewModel: StudentClassViewModel by viewModels()
}