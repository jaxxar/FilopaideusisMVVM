package com.example.filopaideusismvvm.ui.sections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.SectionsAdapter
import com.example.filopaideusismvvm.databinding.FragmentSectionsBinding
import com.example.filopaideusismvvm.viewmodels.SectionsViewModel
import com.example.filopaideusismvvm.viewmodels.SectionsViewModelAssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SectionsFragment : Fragment(R.layout.fragment_sections) {

    private val args: SectionsFragmentArgs by navArgs()

    @Inject
    lateinit var sectionsViewModelFactory: SectionsViewModelAssistedFactory

    private val viewModel: SectionsViewModel by viewModels {
        SectionsViewModel.provideFactory(sectionsViewModelFactory, args.classId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSectionsBinding.bind(view)

        val sectionsAdapter = SectionsAdapter(args.username)

        binding.recyclerViewSections.adapter = sectionsAdapter
        binding.sectionsBackButton.setOnClickListener {
            val action = SectionsFragmentDirections.actionSectionsFragmentToStudentClassFragment(args.username)
            findNavController().navigate(action)
        }

        subscribeUi(sectionsAdapter)
    }

    private fun subscribeUi(adapter: SectionsAdapter) {
        viewModel.sections.observe(viewLifecycleOwner) { sections ->
            adapter.submitList(sections)
        }
    }
}