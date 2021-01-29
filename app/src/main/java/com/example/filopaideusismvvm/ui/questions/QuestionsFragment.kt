package com.example.filopaideusismvvm.ui.questions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.QuestionCallback
import com.example.filopaideusismvvm.adapters.QuestionsAdapter
import com.example.filopaideusismvvm.data.ListQuestionData
import com.example.filopaideusismvvm.data.QuestionData
import com.example.filopaideusismvvm.databinding.FragmentQuestionsBinding
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsFragment : Fragment(R.layout.fragment_questions), QuestionCallback {

    private var totalQuestions: Int = 0
    private var index: Int = 0
    private var questionList = mutableListOf<QuestionData>()

    private val args: QuestionsFragmentArgs by navArgs()

    private lateinit var binding: FragmentQuestionsBinding

    @Inject
    lateinit var questionsViewModelFactory: QuestionViewModel.AssistedFactory

    private val viewModel: QuestionViewModel by viewModels {
        QuestionViewModel.provideFactory(questionsViewModelFactory, args.listQuestionsId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuestionsBinding.bind(view)

        val questionAdapter = QuestionsAdapter(this)

        binding.recyclerViewQuestions.adapter = questionAdapter
        binding.questionsBackButton.setOnClickListener {
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.nextButton.setOnClickListener {
            if (questionList.size != totalQuestions) {
                binding.recyclerViewQuestions.smoothScrollToPosition(0)
                Toast.makeText(activity, getText(R.string.selectAnswer), Toast.LENGTH_SHORT).show()
            } else {
                val listData = ListQuestionData(questionList)
                val action = QuestionsFragmentDirections.actionQuestionsFragmentToResultsFragment(listData)
                findNavController().navigate(action)
            }
        }
        subscribeUi(questionAdapter)
        initUi()
    }

    private fun subscribeUi(adapter: QuestionsAdapter) {
        viewModel.question.observe(viewLifecycleOwner) { questionList ->
            adapter.submitList(questionList)
        }
    }

    private fun initUi() {
        viewModel.totalQuestions.observe(viewLifecycleOwner) { number ->
            binding.questionsCounter.text = number.toString()
            totalQuestions = number
        }
    }

    override fun onQuestionClicked(questionData: QuestionData, answer: String?) {
        questionData.submittedAnswer = answer
        var questionFound = false
        if (questionList.size != 0) {
            for (i in 0 until questionList.size) {
                questionFound = false
                if (questionList[i].id == questionData.id) {
                    questionList.removeAt(i)
                    questionList.add(i, questionData)
                    questionFound = true
                    break
                }
            }
        }
        if (!questionFound) {
            questionList.add(index, questionData)
            index++
        }
    }
}