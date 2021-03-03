package com.example.filopaideusismvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.filopaideusismvvm.data.QuestionData
import com.example.filopaideusismvvm.databinding.DesignQuestionBinding
import com.example.filopaideusismvvm.databinding.DesignThreeQuestionsBinding
import com.example.filopaideusismvvm.databinding.DesignTwoQuestionsBinding
import com.example.filopaideusismvvm.utilities.FOUR_ANSWER_RADIO
import com.example.filopaideusismvvm.utilities.THREE_ANSWER_RADIO
import com.example.filopaideusismvvm.utilities.TWO_ANSWER_RADIO
import com.example.filopaideusismvvm.viewHolders.BaseQuestionViewHolder
import com.example.filopaideusismvvm.viewHolders.FourQuestionsRadioViewHolder
import com.example.filopaideusismvvm.viewHolders.ThreeQuestionsRadioViewHolder
import com.example.filopaideusismvvm.viewHolders.TwoQuestionsRadioViewHolder
import com.example.filopaideusismvvm.viewmodels.QuestionViewModel

class QuestionsAdapter(private val questionViewModel: QuestionViewModel, private val callback: QuestionCallback) :
    ListAdapter<QuestionData, BaseQuestionViewHolder<*, *, *>>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseQuestionViewHolder<*, *, *> {
        return when (viewType) {
            FOUR_ANSWER_RADIO -> {
                val binding =
                    DesignQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FourQuestionsRadioViewHolder(binding)
            }
            THREE_ANSWER_RADIO -> {
                val binding =
                    DesignThreeQuestionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThreeQuestionsRadioViewHolder(binding)
            }
            TWO_ANSWER_RADIO -> {
                val binding =
                    DesignTwoQuestionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TwoQuestionsRadioViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseQuestionViewHolder<*, *, *>, position: Int) {
        val currentItem = getItem(position)
        when (holder) {
            is FourQuestionsRadioViewHolder -> holder.bind(currentItem, questionViewModel, callback)
            is ThreeQuestionsRadioViewHolder -> holder.bind(currentItem, questionViewModel, callback)
            is TwoQuestionsRadioViewHolder -> holder.bind(currentItem, questionViewModel, callback)
            else -> throw IllegalArgumentException("onBindViewHolder error")
        }
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

    override fun getItemViewType(position: Int): Int {
        return questionViewModel.question.value?.get(position)?.questionType!!
    }
}

interface QuestionCallback {
    fun onQuestionClicked()
    fun returnHint(hint: String)
}