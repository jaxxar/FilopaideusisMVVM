package com.example.filopaideusismvvm.ui.questions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.QuestionsAdapter
import com.example.filopaideusismvvm.databinding.FragmentQuestionsBinding
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsFragment : Fragment(R.layout.fragment_questions) {

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

        val questionAdapter = QuestionsAdapter(viewModel)

        binding.recyclerViewQuestions.adapter = questionAdapter
        binding.questionsBackButton.setOnClickListener {
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.nextButton.setOnClickListener {

            binding.recyclerViewQuestions.scrollToPosition(0)

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
        }
    }

}