package com.example.filopaideusismvvm.ui.sections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.SectionsAdapter
import com.example.filopaideusismvvm.databinding.FragmentSectionsBinding
import com.example.filopaideusismvvm.viewmodels.SectionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionsFragment : Fragment(R.layout.fragment_sections) {

    private val viewModel: SectionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSectionsBinding.bind(view)

        val sectionsAdapter = SectionsAdapter()

        binding.apply {
            recyclerViewSections.apply {
                adapter = sectionsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.sections.observe(viewLifecycleOwner) {
            sectionsAdapter.submitList(it)
        }
    }
}