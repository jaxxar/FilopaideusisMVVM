package com.example.filopaideusismvvm.utilities

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private val defaultInterval: Int,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View?) {

        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        v?.let { onSafeClick(it) }
    }
}