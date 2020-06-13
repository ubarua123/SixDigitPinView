package com.wonderquill.sixdigitpinview

import android.content.Context
import android.content.res.TypedArray
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.use

class NumericKeyBoardTransformationMethod : PasswordTransformationMethod() {
    override fun getTransformation(source: CharSequence, view: View?): CharSequence {
        return source
    }
}


fun hideKeyboard(context: Context?, anchor: View) {
    if (context != null) {
        val iMgr =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        iMgr.hideSoftInputFromWindow(anchor.windowToken, 0)
    }
}

fun Context.getStyledAttributes(
    attributeSet: AttributeSet?,
    styleArray: IntArray,
    block: TypedArray.() -> Unit
) =this.obtainStyledAttributes(attributeSet, styleArray, 0, 0).use(block)