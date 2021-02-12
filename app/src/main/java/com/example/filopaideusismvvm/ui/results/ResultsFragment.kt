package com.example.filopaideusismvvm.ui.results

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.ResultsAdapter
import com.example.filopaideusismvvm.data.ListQuestionData
import com.example.filopaideusismvvm.databinding.FragmentResultsBinding
import com.example.filopaideusismvvm.viewmodels.ResultsViewModel

class ResultsFragment : Fragment(R.layout.fragment_results) {

    private var results = ListQuestionData(mutableListOf())
    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var binding: FragmentResultsBinding
    private val viewModel: ResultsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentResultsBinding.bind(view)
        results = args.listData
        val resultsAdapter = ResultsAdapter(results)
        binding.recyclerViewResults.adapter = resultsAdapter
        binding.name.text = args.username
        binding.score.text = getString(R.string.totalAnswered, viewModel.calculateCorrect(results).toString(), viewModel.listSize(results).toString())

        binding.resultsBackButton.setOnClickListener {
            back()
        }
        binding.resultsHomepage.setOnClickListener {

        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                back()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun back() {
        val action = ResultsFragmentDirections.actionResultsFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}