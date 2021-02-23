package com.example.filopaideusismvvm.ui.sections

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.SectionsAdapter
import com.example.filopaideusismvvm.adapters.SectionsCallback
import com.example.filopaideusismvvm.databinding.FragmentSectionsBinding
import com.example.filopaideusismvvm.ui.BaseFragment
import com.example.filopaideusismvvm.viewmodels.SectionsViewModel
import com.example.filopaideusismvvm.viewmodels.SectionsViewModelAssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SectionsFragment : BaseFragment(R.layout.fragment_sections), SectionsCallback {

    private val args: SectionsFragmentArgs by navArgs()

    @Inject
    lateinit var sectionsViewModelFactory: SectionsViewModelAssistedFactory

    private val viewModel: SectionsViewModel by viewModels {
        SectionsViewModel.provideFactory(sectionsViewModelFactory, args.classId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSectionsBinding.bind(view)
        val sectionsAdapter = SectionsAdapter(args.username, args.studentClass, this)

        binding.recyclerViewSections.adapter = sectionsAdapter
        binding.sectionsBackButton.setSafeOnClickListener {
            back()
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                back()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        subscribeUi(sectionsAdapter)
    }

    private fun subscribeUi(adapter: SectionsAdapter) {
        viewModel.sections.observe(viewLifecycleOwner) { sections ->
            adapter.submitList(sections)
        }
    }

    private fun back() {
        val action = SectionsFragmentDirections.actionSectionsFragmentToStudentClassFragment(args.username)
        findNavController().navigate(action)
    }

    override fun returnInfo(info: String) {
        val action = SectionsFragmentDirections.actionSectionsFragmentToDialogFragment(info)
        findNavController().navigate(action)
    }
}