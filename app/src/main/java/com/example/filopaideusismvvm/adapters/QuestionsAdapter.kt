package com.example.filopaideusismvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filopaideusismvvm.data.QuestionData
import com.example.filopaideusismvvm.databinding.DesignQuestionBinding
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel

class QuestionsAdapter(questionViewModel: QuestionViewModel) :
    ListAdapter<QuestionData, QuestionsAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    private val viewModel = questionViewModel

    class QuestionViewHolder(private val binding: DesignQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(questionData: QuestionData, viewModel: QuestionViewModel) {
            binding.apply {
                binding.question.text = questionData.question
                binding.answer1.text = questionData.answer1
                binding.answer2.text = questionData.answer2
                binding.answer3.text = questionData.answer3
                binding.answer4.text = questionData.answer4
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            DesignQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, viewModel)
    }

    private class QuestionDiffCallback : DiffUtil.ItemCallback<QuestionData>() {

        override fun areItemsTheSame(
            oldItem: QuestionData,
            newItem: QuestionData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: QuestionData,
            newItem: QuestionData
        ): Boolean = oldItem == newItem
    }
}