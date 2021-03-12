package com.chengwf.utils.callback

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView

class EditorActionListener(
        private val search: () -> Unit = {},
        private val send: () -> Unit = {},
        private val other: () -> Unit = {}
) : TextView.OnEditorActionListener {
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> search()
            EditorInfo.IME_ACTION_SEND -> send()
            else -> other()
        }

        return false
    }

}