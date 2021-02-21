package com.example.filopaideusismvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.filopaideusismvvm.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFragment : BottomSheetDialogFragment() {

    private val args: DialogFragmentArgs by navArgs()
    private lateinit var binding: FragmentDialogBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialog.text = args.hint
    }
}