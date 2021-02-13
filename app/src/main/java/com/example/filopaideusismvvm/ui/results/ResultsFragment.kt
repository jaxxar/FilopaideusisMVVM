package com.example.filopaideusismvvm.ui.results

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.ResultsAdapter
import com.example.filopaideusismvvm.data.ListQuestionData
import com.example.filopaideusismvvm.databinding.FragmentResultsBinding
import com.example.filopaideusismvvm.ui.BaseFragment
import com.example.filopaideusismvvm.viewmodels.ResultsViewModel

class ResultsFragment : BaseFragment(R.layout.fragment_results) {

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

        binding.resultsBackButton.setSafeOnClickListener {
            back()
        }
        binding.resultsShare.setSafeOnClickListener {
            share()
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

    private fun share() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = getShareBody()
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.shareVia)))
    }

    private fun getShareBody(): String {
        return args.username + " " + getString(R.string.totalAnswered, viewModel.calculateCorrect(results).toString(), viewModel.listSize(results).toString())
    }
}