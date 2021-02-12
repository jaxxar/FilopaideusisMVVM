package com.example.filopaideusismvvm.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.example.filopaideusismvvm.utilities.SAFE_CLICK_LISTENER_DEFAULT_INTERVAL
import com.example.filopaideusismvvm.utilities.SafeClickListener

open class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener =
            SafeClickListener(SAFE_CLICK_LISTENER_DEFAULT_INTERVAL) {
                onSafeClick(it)
            }
        setOnClickListener(safeClickListener)
    }
}