package com.example.filopaideusismvvm.ui.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.databinding.FragmentLoginBinding
import com.example.filopaideusismvvm.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.apply {
            loginButton.setOnClickListener {
                viewModel.validateData(binding.nameInputEditText.text.toString())
            }
            nameInputEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.validateData(binding.nameInputEditText.text.toString())
                    true
                } else false
            }
        }
        initUI()
    }

    private fun initUI() {
        viewModel.usernameLiveData.observe(viewLifecycleOwner, { isValid ->
            if (isValid) {
                openActivity()
                binding.nameInputEditTextLabel.error = null
            } else {
                binding.nameInputEditTextLabel.error = getString(R.string.selectName)
            }
        })
    }

    private fun openActivity() {
        val action =
            LoginFragmentDirections.actionLoginFragmentToStudentClassFragment()
        findNavController().navigate(action)
    }
}