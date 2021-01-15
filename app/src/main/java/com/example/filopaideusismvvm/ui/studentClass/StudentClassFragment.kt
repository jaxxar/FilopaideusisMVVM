package com.example.filopaideusismvvm.ui.studentClass

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.StudentClassAdapter
import com.example.filopaideusismvvm.databinding.FragmentStudentClassBinding
import com.example.filopaideusismvvm.viewmodels.StudentClassViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentClassFragment : Fragment(R.layout.fragment_student_class) {

    private val viewModel: StudentClassViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentStudentClassBinding.bind(view)

        val studentClassAdapter = StudentClassAdapter()

        binding.apply {
            recyclerViewSections.apply {
                adapter = studentClassAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            sectionsBackButton.setOnClickListener {
                val action =
                    StudentClassFragmentDirections.actionStudentClassFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.studentClass.observe(viewLifecycleOwner) {
            studentClassAdapter.submitList(it)
        }
    }
}