package com.example.filopaideusismvvm.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.databinding.FragmentLoginBinding

private lateinit var binding: FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.apply {
            loginButton.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToStudentClassFragment()
                findNavController().navigate(action)
            }
        }
    }

}