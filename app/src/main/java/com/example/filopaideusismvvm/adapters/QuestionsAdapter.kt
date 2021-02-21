package com.example.filopaideusismvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
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
                binding.answer3.visibility = View.VISIBLE
                binding.answer4.visibility = View.VISIBLE
                binding.question.text = questionData.question?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
                binding.answer1.text = questionData.answer1?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
                binding.answer2.text = questionData.answer2?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
                if (questionData.answer3.isNullOrEmpty()) {
                    binding.answer3.visibility = View.GONE
                } else {
                    binding.answer3.text = questionData.answer3.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
                }
                if (questionData.answer4.isNullOrEmpty()) {
                    binding.answer4.visibility = View.GONE
                } else {
                    binding.answer4.text = questionData.answer4.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
                }
                if (questionData.hint.isNullOrEmpty()) {
                    binding.helpButton.visibility = View.GONE
                } else {
                    binding.helpButton.visibility = View.VISIBLE
                    binding.helpButton.setOnClickListener {
                        callback.returnHint(questionData.hint)
                    }
                }
                binding.answer1.isChecked = false
                binding.answer2.isChecked = false
                binding.answer3.isChecked = false
                binding.answer4.isChecked = false
                when (questionViewModel.checkChecked(questionData)) {
                    1 -> {
                        binding.answer1.isChecked = true
                    }
                    2 -> {
                        binding.answer2.isChecked = true
                    }
                    3 -> {
                        binding.answer3.isChecked = true
                    }
                    4 -> {
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
    fun returnHint(hint: String)
}