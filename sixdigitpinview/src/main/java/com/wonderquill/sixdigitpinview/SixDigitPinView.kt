package com.wonderquill.sixdigitpinview

import android.content.Context
import android.os.Handler
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.wonderquill.sixdigitpinview.databinding.PinInputLayoutBinding

/**
 * A very lightweight library which serves the purpose of entering a 6 digit pix like OTP from an SMS.
 * Uses latest Material IO and theme is picked up from whatever is set in the app.
 */
class SixDigitPinView : LinearLayout {

    private lateinit var binding: PinInputLayoutBinding
    private var selectedIndex: Int = 0
    private val editTextList: ArrayList<EditText> = arrayListOf()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.let {

            var showReset = false
            context.getStyledAttributes(it, R.styleable.SixDigitPinView) {
                showReset = getBoolean(R.styleable.SixDigitPinView_show_reset_button, false)
            }

            binding = PinInputLayoutBinding.inflate(LayoutInflater.from(context), this, true)

            editTextList.add(binding.et0.apply {
                filters = arrayOf(InputFilter { source, _, end, _, dstart, dend ->
                    try {
                        if (end == 0 || dstart < dend) {
                            // backspace was pressed! handle accordingly
                        } else {
                            editTextList[++selectedIndex].requestFocus()
                        }
                    } catch (e: Exception) {
                    }
                    source
                }, InputFilter.LengthFilter(1))
                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = NumericKeyBoardTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus)
                        selectedIndex = 0
                }
            })

            editTextList.add(binding.et1.apply {
                filters = arrayOf(InputFilter { source, _, end, _, dstart, dend ->
                    try {
                        if (end == 0 || dstart < dend) {
                            // backspace was pressed! handle accordingly
                            editTextList[--selectedIndex].requestFocus()
                        } else {
                            editTextList[++selectedIndex].requestFocus()
                        }
                    } catch (e: Exception) {
                    }
                    source
                }, InputFilter.LengthFilter(1))
                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = NumericKeyBoardTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus)
                        selectedIndex = 1
                }
            })

            editTextList.add(binding.et2.apply {
                filters = arrayOf(InputFilter { source, _, end, _, dstart, dend ->
                    try {
                        if (end == 0 || dstart < dend) {
                            // backspace was pressed! handle accordingly
                            editTextList[--selectedIndex].requestFocus()
                        } else {
                            editTextList[++selectedIndex].requestFocus()
                        }
                    } catch (e: Exception) {
                    }
                    source
                }, InputFilter.LengthFilter(1))

                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = NumericKeyBoardTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus)
                        selectedIndex = 2
                }
            })

            editTextList.add(binding.et3.apply {
                filters = arrayOf(InputFilter { source, _, end, _, dstart, dend ->
                    try {
                        if (end == 0 || dstart < dend) {
                            // backspace was pressed! handle accordingly
                            editTextList[--selectedIndex].requestFocus()
                        } else {
                            editTextList[++selectedIndex].requestFocus()
                        }
                    } catch (e: Exception) {
                    }
                    source
                }, InputFilter.LengthFilter(1))

                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = NumericKeyBoardTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus)
                        selectedIndex = 3
                }
            })

            editTextList.add(binding.et4.apply {
                filters = arrayOf(InputFilter { source, _, end, _, dstart, dend ->
                    try {
                        if (end == 0 || dstart < dend) {
                            // backspace was pressed! handle accordingly
                            editTextList[--selectedIndex].requestFocus()
                        } else {
                            editTextList[++selectedIndex].requestFocus()
                        }
                    } catch (e: Exception) {
                    }
                    source
                }, InputFilter.LengthFilter(1))

                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = NumericKeyBoardTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus)
                        selectedIndex = 4
                }
            })

            editTextList.add(binding.et5.apply {
                filters = arrayOf(InputFilter { source, _, end, _, dstart, dend ->
                    try {
                        if (end == 0 || dstart < dend) {
                            // backspace was pressed! handle accordingly
                            editTextList[--selectedIndex].requestFocus()
                        } else {
                            Handler().postDelayed({
                                hideKeyboard(context, this)
                            }, 50)
                        }
                    } catch (e: Exception) {
                    }
                    // this is the last one
                    source
                }, InputFilter.LengthFilter(1))

                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = NumericKeyBoardTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus)
                        selectedIndex = 5
                }
            })

            if (showReset) {
                binding.reset.visibility = View.VISIBLE
                binding.reset.setOnClickListener {
                    reset()
                }
            }
            else {
                binding.reset.visibility = View.GONE
            }
        } // attr end
    }

    /**
     * Resets the views to empty and changes focus to the first field
     */
    fun reset() {
        editTextList.forEach {
            it.text.clear()
        }
        editTextList[0].requestFocus()
        selectedIndex = 0
    }

    fun getEnteredPin(): String {
        val stringB = StringBuilder()
        editTextList.forEach {
            stringB.append(it.text.toString())
        }
        return stringB.toString()
    }

    /**
     * Use this method to set a pin programmatically, for e.g while auto detecting otp sms or copy paste.
     * Note: You need to write the copy logic yourself. This method can be called for "pasting"
     * Accepts only Numeric. If not numeric, nothing happens.
     *
     */
    fun pastePin(pin: String) {
        if (pin.length == 6) {
            // Check if all characters a numbers
            if ("[0-9]*".toRegex().matches(pin)) {
                for (c in pin.indices) {
                    editTextList[c].setText(pin[c].toString())
                }
            }
        }
    }

}