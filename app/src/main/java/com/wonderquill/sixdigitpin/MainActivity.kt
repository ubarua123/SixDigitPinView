package com.wonderquill.sixdigitpin

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wonderquill.sixdigitpinview.SixDigitPinView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pinView = findViewById<SixDigitPinView>(R.id.sixDigitPinView)

        findViewById<Button>(R.id.button).setOnClickListener {
            val pin = pinView.getEnteredPin()
            Toast.makeText(applicationContext, "Entered Pin [$pin]", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.primaryClip?.let {
                if(it.itemCount > 0)
                    pinView.pastePin(it.getItemAt(0).text.toString())
            }
        }
    }
}