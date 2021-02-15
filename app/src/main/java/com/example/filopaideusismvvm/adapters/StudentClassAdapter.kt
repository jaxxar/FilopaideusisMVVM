package com.example.filopaideusismvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filopaideusismvvm.data.StudentClassData
import com.example.filopaideusismvvm.databinding.DesignSectionsBinding
import com.example.filopaideusismvvm.ui.studentClass.StudentClassFragmentDirections

class StudentClassAdapter(private val username: String) :
    ListAdapter<StudentClassData, StudentClassAdapter.StudentClassViewHolder>(
        StudentClassDiffCallback()
    ) {


    class StudentClassViewHolder(private val binding: DesignSectionsBinding, private val username: String) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(studentClassData: StudentClassData) {
            binding.apply {
                sectionsButtonText.text = studentClassData.title
                sectionsButton.setOnClickListener {
                    val action =
                        StudentClassFragmentDirections.actionStudentClassFragmentToSectionsFragment(
                            studentClassData.id,
                            username,
                            studentClassData.title
                        )
                    findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentClassViewHolder {
        val binding =
            DesignSectionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentClassViewHolder(binding, username)
    }

    override fun onBindViewHolder(holder: StudentClassViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    private class StudentClassDiffCallback : DiffUtil.ItemCallback<StudentClassData>() {

        override fun areItemsTheSame(
            oldItem: StudentClassData,
            newItem: StudentClassData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: StudentClassData,
            newItem: StudentClassData
        ): Boolean = oldItem == newItem
    }
}