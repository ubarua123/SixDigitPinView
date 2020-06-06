package com.wonderquill.sixdigitpinview

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager

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