package com.example.filopaideusismvvm.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.filopaideusismvvm.data.ListQuestionData
import com.example.filopaideusismvvm.databinding.DesignResultBinding

class ResultsAdapter(
    private val results: ListQuestionData
) : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    private lateinit var binding: DesignResultBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DesignResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val answer1 = results.list?.get(position)?.answer1
        val answer2 = results.list?.get(position)?.answer2
        val answer3 = results.list?.get(position)?.answer3
        val answer4 = results.list?.get(position)?.answer4
        val correctAnswer = results.list?.get(position)?.correctAnswer
        val submittedAnswer = results.list?.get(position)?.submittedAnswer
        holder.question.text = results.list?.get(position)?.question.let { HtmlCompat.fromHtml(it!!, HtmlCompat.FROM_HTML_MODE_LEGACY) }
        resultCheck(answer1, correctAnswer, submittedAnswer, holder.answer1)
        resultCheck(answer2, correctAnswer, submittedAnswer, holder.answer2)
        resultCheck(answer3, correctAnswer, submittedAnswer, holder.answer3)
        resultCheck(answer4, correctAnswer, submittedAnswer, holder.answer4)
    }

    override fun getItemCount(): Int {
        return if (results.list != null) results.list.size
        else 0
    }

    class ViewHolder(binding: DesignResultBinding) : RecyclerView.ViewHolder(binding.root) {
        var question: TextView = binding.question
        var answer1: TextView = binding.answer1
        var answer2: TextView = binding.answer2
        var answer3: TextView = binding.answer3
        var answer4: TextView = binding.answer4
    }

    private fun resultCheck(
        answer: String?,
        correctAnswer: String?,
        submittedAnswer: String?,
        view: TextView
    ) {
        view.text = answer.let { HtmlCompat.fromHtml(it!!, HtmlCompat.FROM_HTML_MODE_LEGACY) }
        if (answer == submittedAnswer) {
            if (submittedAnswer == correctAnswer) {
                view.setTextColor(Color.GREEN)
            } else {
                view.setTextColor(Color.RED)
            }
        } else if (answer == correctAnswer) {
            view.setTextColor(Color.GREEN)
        }
    }
}