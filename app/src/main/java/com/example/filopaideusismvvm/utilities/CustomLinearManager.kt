package com.example.filopaideusismvvm.utilities

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLinearManager(context: FragmentActivity, orientation: Int) : LinearLayoutManager(context, orientation, false) {

    private var isScrollEnabled = false

    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollHorizontally(): Boolean {
        return isScrollEnabled
    }

}