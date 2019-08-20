package com.weight.weightcoder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.weight.weightcoder.coder.WeightCode
import kotlinx.android.synthetic.*
import android.view.View.OnFocusChangeListener
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.result_text_view)
        var editText = findViewById<EditText>(R.id.input_text)
        editText.hint = "The input text"
        textView.text = WeightCode.code("ASD")

        var okButton = findViewById<Button>(R.id.ok_button)
        var modeButton = findViewById<Button>(R.id.mode_button)
        var pasteButton = findViewById<Button>(R.id.paste_button)
        var copyButton = findViewById<Button>(R.id.copy_button)
        var clearButton = findViewById<Button>(R.id.clear_button)
        var codeText = true

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
            editText.hideKeayboard()
        }

        modeButton.setOnClickListener {
            codeText = !codeText
            if (codeText) {
                modeButton.text = "Code"
            } else {
                modeButton.text = "Decode"
            }
        }



        clearButton.setOnClickListener {
            editText.text.clear()
        }
    }

    fun View.hideKeayboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}
