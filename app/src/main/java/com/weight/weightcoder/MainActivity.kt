package com.weight.weightcoder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.weight.weightcoder.coder.WeightCode
import android.widget.Button
import java.lang.Exception
import android.view.inputmethod.InputMethodManager
import android.text.method.ScrollingMovementMethod




class MainActivity : AppCompatActivity() {

    private var myClipboard: ClipboardManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.result_text_view)
        textView.movementMethod = ScrollingMovementMethod()
        val editText = findViewById<EditText>(R.id.input_text)
        editText.hint = "The input text"

        val okButton = findViewById<Button>(R.id.ok_button)
        val modeButton = findViewById<Button>(R.id.mode_button)
        val pasteButton = findViewById<Button>(R.id.paste_button)
        val copyButton = findViewById<Button>(R.id.copy_button)
        val clearButton = findViewById<Button>(R.id.clear_button)
        var codeText = true
        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?

        okButton.setOnClickListener {
            editText.clearFocus()
            textView.text = ""
            if (codeText) {
                textView.text = WeightCode.code(editText.text.toString())
            } else {
                try {
                    textView.text = WeightCode.decode(editText.text.toString())
                } catch (e: Exception) {
                    textView.text = "Wrong code."
                }
            }
            editText.hideKeyboard()
        }

        modeButton.setOnClickListener {
            codeText = !codeText
            if (codeText) {
                modeButton.text = "Code"
            } else {
                modeButton.text = "Decode"
            }
        }

        copyButton.setOnClickListener {
            myClipboard?.setPrimaryClip(ClipData.newPlainText("text", textView.text))
        }

        pasteButton.setOnClickListener {
            val abc = myClipboard?.getPrimaryClip()
            val item = abc?.getItemAt(0)

            editText.setText(item?.text.toString())
        }

        clearButton.setOnClickListener {
            editText.text.clear()
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}
