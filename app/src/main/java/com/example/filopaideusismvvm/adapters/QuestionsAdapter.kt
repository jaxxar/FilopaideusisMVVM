package com.example.filopaideusismvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filopaideusismvvm.data.QuestionData
import com.example.filopaideusismvvm.databinding.DesignQuestionBinding
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel

class QuestionsAdapter(private val questionViewModel: QuestionViewModel, private val callback: QuestionCallback) :
    ListAdapter<QuestionData, QuestionsAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    class QuestionViewHolder(private val binding: DesignQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(questionData: QuestionData, questionViewModel: QuestionViewModel, callback: QuestionCallback) {
            binding.apply {
                binding.question.text = questionData.question
                binding.answer1.text = questionData.answer1
                binding.answer2.text = questionData.answer2
                binding.answer3.text = questionData.answer3
                binding.answer4.text = questionData.answer4
                when {
                    questionViewModel.checkChecked(questionData) == 1 -> {
                        binding.answer1.isChecked = true
                    }
                    questionViewModel.checkChecked(questionData) == 2 -> {
                        binding.answer2.isChecked = true
                    }
                    questionViewModel.checkChecked(questionData) == 3 -> {
                        binding.answer3.isChecked = true
                    }
                    questionViewModel.checkChecked(questionData) == 4 -> {
                        binding.answer4.isChecked = true
                    }
                }
                binding.answer1.setOnClickListener {
                    questionData.checked1 = true
                    questionData.checked2 = false
                    questionData.checked3 = false
                    questionData.checked4 = false
                    questionData.submittedAnswer = questionData.answer1
                    questionViewModel.addToList(questionData)
                    callback.onQuestionClicked()
                }
                binding.answer2.setOnClickListener {
                    questionData.checked1 = false
                    questionData.checked2 = true
                    questionData.checked3 = false
                    questionData.checked4 = false
                    questionData.submittedAnswer = questionData.answer2
                    questionViewModel.addToList(questionData)
                    callback.onQuestionClicked()
                }
                binding.answer3.setOnClickListener {
                    questionData.checked1 = false
                    questionData.checked2 = false
                    questionData.checked3 = true
                    questionData.checked4 = false
                    questionData.submittedAnswer = questionData.answer3
                    questionViewModel.addToList(questionData)
                    callback.onQuestionClicked()
                }
                binding.answer4.setOnClickListener {
                    questionData.checked1 = false
                    questionData.checked2 = false
                    questionData.checked3 = false
                    questionData.checked4 = true
                    questionData.submittedAnswer = questionData.answer4
                    questionViewModel.addToList(questionData)
                    callback.onQuestionClicked()
                }
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
        holder.bind(currentItem, questionViewModel, callback)
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

interface QuestionCallback {

    fun onQuestionClicked()
}