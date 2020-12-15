package com.chengwf.demo.utils.callback

import android.text.Editable
import android.text.TextWatcher

/**
 * exitTextView的输入检查监听
 * <p>
 * 大部分时候只需要某一个回调方法，另外两个也写出来完全是多余的
 */
class CommonTextWatcher(
        private val afterChanged: ((Editable?) -> Unit) = {},
        private val beforeChanged: ((CharSequence?, Int, Int, Int) -> Unit) = { _, _, _, _ -> },
        private val onChanged: ((CharSequence?, Int, Int, Int) -> Unit) = { _, _, _, _ -> }
) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        afterChanged.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeChanged.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged.invoke(s, start, before, count)
    }
}