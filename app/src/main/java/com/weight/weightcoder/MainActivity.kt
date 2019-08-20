package com.weight.weightcoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.weight.weightcoder.coder.WeightCode
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.result_text_view)
        textView.text = WeightCode.code("ASD")

    }

}
