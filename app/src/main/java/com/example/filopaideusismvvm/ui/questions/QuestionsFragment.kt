package com.example.filopaideusismvvm.ui.questions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.QuestionCallback
import com.example.filopaideusismvvm.adapters.QuestionsAdapter
import com.example.filopaideusismvvm.data.ListQuestionData
import com.example.filopaideusismvvm.databinding.FragmentQuestionsBinding
import com.example.filopaideusismvvm.ui.BaseFragment
import com.example.filopaideusismvvm.utilities.CustomLinearManager
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel
import com.example.filopaideusismvvm.viewmodels.QuestionViewModelAssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsFragment : BaseFragment(R.layout.fragment_questions), QuestionCallback {

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

        val layoutManager = CustomLinearManager(requireActivity(), RecyclerView.HORIZONTAL)
        binding.recyclerViewQuestions.layoutManager = layoutManager
        binding.recyclerViewQuestions.adapter = questionAdapter
        binding.questionsBackButton.setOnClickListener {
            back()
        }
        binding.nextButton.setSafeOnClickListener {
            if (viewModel.returnListSize() != totalQuestions) {
                layoutManager.setScrollEnabled(true)
                binding.recyclerViewQuestions.scrollToPosition(viewModel.findUnchecked())
                Toast.makeText(activity, getText(R.string.selectAnswer), Toast.LENGTH_SHORT).show()
                layoutManager.setScrollEnabled(false)
            } else {
                val listData = ListQuestionData(viewModel.returnList())
                val action = QuestionsFragmentDirections.actionQuestionsFragmentToResultsFragment(listData, args.username, args.studentClass, args.topic, viewModel.returnTime())
                findNavController().navigate(action)
            }
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                back()
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
            onQuestionClicked()
        }
    }

    override fun onQuestionClicked() {
        binding.questionsCounter.text = getString(R.string.totalAnswered, viewModel.returnListSize().toString(), viewModel.totalQuestions.value.toString())
    }

    override fun returnHint(hint: String) {
        Toast.makeText(activity, hint, Toast.LENGTH_SHORT).show()
    }

    private fun back() {
        val action = QuestionsFragmentDirections.actionQuestionsFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}