package com.example.filopaideusismvvm.ui.results

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.ResultsAdapter
import com.example.filopaideusismvvm.data.ListQuestionData
import com.example.filopaideusismvvm.databinding.FragmentResultsBinding

class ResultsFragment : Fragment(R.layout.fragment_results) {

    private var results = ListQuestionData(mutableListOf())

    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var binding: FragmentResultsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentResultsBinding.bind(view)
        results = args.listData

        val resultsAdapter = ResultsAdapter(results)
        binding.recyclerViewResults.adapter = resultsAdapter
        binding.name.text = args.username

        binding.resultsBackButton.setOnClickListener {
            val action = ResultsFragmentDirections.actionResultsFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.resultsHomepage.setOnClickListener {

        }
    }
}