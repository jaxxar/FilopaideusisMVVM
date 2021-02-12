package com.example.filopaideusismvvm.ui.studentClass

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.adapters.StudentClassAdapter
import com.example.filopaideusismvvm.databinding.FragmentStudentClassBinding
import com.example.filopaideusismvvm.viewmodels.StudentClassViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentClassFragment : Fragment(R.layout.fragment_student_class) {

    private val viewModel: StudentClassViewModel by viewModels()
    private val args: StudentClassFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentStudentClassBinding.bind(view)

        val studentClassAdapter = StudentClassAdapter(args.username)

        binding.recyclerViewStudentClass.adapter = studentClassAdapter
        binding.studentClassBackButton.setOnClickListener {
            val action =
                StudentClassFragmentDirections.actionStudentClassFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        subscribeUi(studentClassAdapter)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = StudentClassFragmentDirections.actionStudentClassFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

    private fun subscribeUi(adapter: StudentClassAdapter) {
        viewModel.studentClass.observe(viewLifecycleOwner) { studentClass ->
            adapter.submitList(studentClass)
        }
    }
}