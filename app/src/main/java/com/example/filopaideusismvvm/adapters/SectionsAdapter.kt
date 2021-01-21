package com.example.filopaideusismvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filopaideusismvvm.data.SectionsData
import com.example.filopaideusismvvm.databinding.DesignSectionsBinding
import com.example.filopaideusismvvm.ui.sections.SectionsFragmentDirections

class SectionsAdapter :
    ListAdapter<SectionsData, SectionsAdapter.SectionViewHolder>(
        SectionDiffCallback()
    ) {

    class SectionViewHolder(private val binding: DesignSectionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sectionsData: SectionsData) {
            binding.apply {
                sectionsButtonText.text = sectionsData.title
                sectionsButton.setOnClickListener {
                    val action =
                        SectionsFragmentDirections.actionSectionsFragmentToQuestionsFragment(
                            sectionsData.listQuestionsId
                        )
                    findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding =
            DesignSectionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    private class SectionDiffCallback : DiffUtil.ItemCallback<SectionsData>() {

        override fun areItemsTheSame(
            oldItem: SectionsData,
            newItem: SectionsData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SectionsData,
            newItem: SectionsData
        ): Boolean = oldItem == newItem
    }
}