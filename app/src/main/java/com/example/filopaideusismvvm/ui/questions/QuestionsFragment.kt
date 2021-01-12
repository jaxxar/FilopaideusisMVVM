package com.example.filopaideusismvvm.ui.questions

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    private val viewModel: QuestionViewModel by viewModels()
}