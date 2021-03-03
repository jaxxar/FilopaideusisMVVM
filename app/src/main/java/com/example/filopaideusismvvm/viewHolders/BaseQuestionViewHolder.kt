package com.example.filopaideusismvvm.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseQuestionViewHolder<D, VM, C>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: D, viewModel: VM, callback: C)
}