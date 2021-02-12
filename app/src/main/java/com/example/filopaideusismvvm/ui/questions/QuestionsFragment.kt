package com.example.filopaideusismvvm.ui.questions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.QuestionCallback
import com.example.filopaideusismvvm.adapters.QuestionsAdapter
import com.example.filopaideusismvvm.data.ListQuestionData
import com.example.filopaideusismvvm.databinding.FragmentQuestionsBinding
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel
import com.example.filopaideusismvvm.viewmodels.QuestionViewModelAssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsFragment : Fragment(R.layout.fragment_questions), QuestionCallback {

    private var totalQuestions: Int = 0

    private val args: QuestionsFragmentArgs by navArgs()

    private lateinit var binding: FragmentQuestionsBinding

    @Inject
    lateinit var questionsViewModelFactory: QuestionViewModelAssistedFactory

    private val viewModel: QuestionViewModel by viewModels {
        QuestionViewModel.provideFactory(questionsViewModelFactory, args.listQuestionsId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuestionsBinding.bind(view)

        val questionAdapter = QuestionsAdapter(viewModel, this)

        binding.recyclerViewQuestions.adapter = questionAdapter
        binding.questionsBackButton.setOnClickListener {
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.nextButton.setOnClickListener {
            if (viewModel.returnListSize() != totalQuestions) {
                binding.recyclerViewQuestions.smoothScrollToPosition(viewModel.findUnchecked())
                Toast.makeText(activity, getText(R.string.selectAnswer), Toast.LENGTH_SHORT).show()
            } else {
                val listData = ListQuestionData(viewModel.returnList())
                val action = QuestionsFragmentDirections.actionQuestionsFragmentToResultsFragment(listData, args.username)
                findNavController().navigate(action)
            }
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = QuestionsFragmentDirections.actionQuestionsFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
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
            totalQuestions = number
        }
        onQuestionClicked()
    }

    override fun onQuestionClicked() {
        binding.questionsCounter.text = getString(R.string.totalAnswered, viewModel.returnListSize().toString(), viewModel.totalQuestions.value.toString())
    }
}