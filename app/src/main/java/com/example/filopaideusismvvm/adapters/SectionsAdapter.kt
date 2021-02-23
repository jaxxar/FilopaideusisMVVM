package com.example.filopaideusismvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filopaideusismvvm.data.SectionsData
import com.example.filopaideusismvvm.databinding.DesignSectionsBinding
import com.example.filopaideusismvvm.ui.sections.SectionsFragmentDirections

class SectionsAdapter(private val username: String, private val studentClass: String, private val callback: SectionsCallback) :
    ListAdapter<SectionsData, SectionsAdapter.SectionViewHolder>(
        SectionDiffCallback()
    ) {

    class SectionViewHolder(private val binding: DesignSectionsBinding, private val username: String, private val studentClass: String) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sectionsData: SectionsData, callback: SectionsCallback) {
            binding.apply {
                sectionsButtonText.text = sectionsData.title
                if (!sectionsData.subTitle.isNullOrBlank()) {
                    sectionsButtonSubText.text = sectionsData.subTitle
                }
                if (sectionsData.info.isNullOrEmpty()) {
                    binding.infoButton.visibility = View.GONE
                } else {
                    binding.infoButton.visibility = View.VISIBLE
                    binding.infoButton.setOnClickListener {
                        callback.returnInfo(sectionsData.info)
                    }
                }
                sectionsButton.setOnClickListener {
                    val topic = if (!sectionsData.subTitle.isNullOrEmpty()) sectionsData.title + " " + sectionsData.subTitle
                    else sectionsData.title
                    val action =
                        SectionsFragmentDirections.actionSectionsFragmentToQuestionsFragment(
                            sectionsData.listQuestionsId,
                            username,
                            studentClass,
                            topic
                        )
                    findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding =
            DesignSectionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding, username, studentClass)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, callback)
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

interface SectionsCallback {
    fun returnInfo(info: String)
}