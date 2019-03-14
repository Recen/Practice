package com.recen.dotui.view

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import java.util.regex.Pattern

class PhoneEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatEditText(context, attrs, defStyleAttr), TextWatcher {

    var iOnBussinessListener: IOnBussinessListener? = null
    // 获得不包含空格的手机号
    val phoneText: String
        get() {
            val str = text.toString()
            return replaceBlank(str)
        }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        super.onTextChanged(s, start, before, count)
        iOnBussinessListener?.onTextChanged4Business()
        if (s == null || s.isEmpty()) {
            return
        }
        val sb = StringBuilder()
        if (s.length != PHONE_INDEX_4 || s.length != PHONE_INDEX_9){
            if (s[s.length-1].toString() == " "){
                setText(text.toString().trim { it <= ' ' })
                setSelection(text.toString().length)
                return
            }
        }
        for (i in 0 until s.length) {
            if (i == PHONE_INDEX_3 || i == PHONE_INDEX_8 || s[i] != ' ') {
                sb.append(s[i])
                if ((sb.length == PHONE_INDEX_4 || sb.length == PHONE_INDEX_9) && sb[sb.length - 1] != ' ') {
                    sb.insert(sb.length - 1, ' ')
                }
            }
        }


        if (sb.toString() != s.toString()) {
            var index = start + 1
            if (sb[start] == ' ') {
                if (before == 0) {
                    index++
                } else {
                    index--
                }
            } else {
                if (before == 1) {
                    index--
                }
            }
            setText(sb.toString())
            setSelection(index)
        }

        if (text.toString().endsWith(" ")) {
            setText(text.toString().trim { it <= ' ' })
            setSelection(text.toString().length)
        }
    }

    override fun afterTextChanged(s: Editable) {
    }

    private fun replaceBlank(str: String?): String {
        var dest = ""
        if (str != null) {
            val p = Pattern.compile("\\s*|\t|\r|\n")
            val m = p.matcher(str)
            if (m.find()) {
                dest = m.replaceAll("")
            }
        }
        return dest
    }

    interface IOnBussinessListener {
        fun onTextChanged4Business()
    }

    companion object {
        // 特殊下标位置
        private const val PHONE_INDEX_3 = 3
        private const val PHONE_INDEX_4 = 4
        private const val PHONE_INDEX_8 = 8
        private const val PHONE_INDEX_9 = 9
    }
}