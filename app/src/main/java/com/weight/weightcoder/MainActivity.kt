package com.weight.weightcoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.weight.weightcoder.coder.WeightCode
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.result_text_view)
        var editText = findViewById<EditText>(R.id.input_text)
        editText.hint = "The input text"
        textView.text = WeightCode.code("ASD")

    }

}
