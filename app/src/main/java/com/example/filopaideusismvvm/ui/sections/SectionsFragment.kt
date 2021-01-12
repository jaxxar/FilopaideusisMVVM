package com.example.filopaideusismvvm.ui.sections

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.filopaideusismvvm.R
import com.example.filopaideusismvvm.viewmodels.SectionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionsFragment : Fragment(R.layout.fragment_sections) {

    private val viewModel: SectionsViewModel by viewModels()
}