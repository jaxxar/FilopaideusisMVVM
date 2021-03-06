package com.example.filopaideusismvvm.viewHolders


import android.view.View
import androidx.core.text.HtmlCompat
import com.example.filopaideusismvvm.adapters.QuestionCallback
import com.example.filopaideusismvvm.data.QuestionData
import com.example.filopaideusismvvm.databinding.DesignTwoQuestionsBinding
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel

class TwoQuestionsRadioViewHolder(private val binding: DesignTwoQuestionsBinding) : BaseQuestionViewHolder<QuestionData, QuestionViewModel, QuestionCallback>(binding.root) {

    override fun bind(data: QuestionData, viewModel: QuestionViewModel, callback: QuestionCallback) {
        binding.apply {
            binding.question.text = data.question?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
            binding.answer1.text = data.answer1?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
            binding.answer2.text = data.answer2?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
            if (data.hint.isNullOrEmpty()) {
                binding.helpButton.visibility = View.GONE
            } else {
                binding.helpButton.visibility = View.VISIBLE
                binding.helpButton.setOnClickListener {
                    callback.returnHint(data.hint)
                }
            }
            binding.answer1.isChecked = false
            binding.answer2.isChecked = false
            when (viewModel.checkChecked(data)) {
                1 -> {
                    binding.answer1.isChecked = true
                }
                2 -> {
                    binding.answer2.isChecked = true
                }
            }
            binding.answer1.setOnClickListener {
                data.checked1 = true
                data.checked2 = false
                data.checked3 = false
                data.checked4 = false
                data.submittedAnswer = data.answer1
                viewModel.addToList(data)
                callback.onQuestionClicked()
            }
            binding.answer2.setOnClickListener {
                data.checked1 = false
                data.checked2 = true
                data.checked3 = false
                data.checked4 = false
                data.submittedAnswer = data.answer2
                viewModel.addToList(data)
                callback.onQuestionClicked()
            }
        }
    }
}