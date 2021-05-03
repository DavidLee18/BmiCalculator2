package com.example.bmicalculator2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.layout_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    fun buttonOnClick(v: View) {
        startActivity<ResultActivity>(
            "height" to height.text.toString().toDouble(),
            "weight" to weight.text.toString().toDouble()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = getSharedPreferences("bmi2", Context.MODE_PRIVATE)
        setContentView(R.layout.layout_main)

        height.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {} override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = s.toString()
                try {
                    if (str.toDouble() !in 100.0 .. 200.0) {
                        height_layout.error = "100에서 200 사이의 수를 입력하세요"
                        button.isEnabled = false
                    }
                    else { height_layout.error = null; button.isEnabled = true }
                } catch (e: Exception) {
                    height_layout.error = "숫자를 입력하세요"; button.isEnabled = false
                }
            }
        })
        weight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {} override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = s.toString()
                try {
                    if(str.toDouble() !in 30.0 .. 150.0) {
                        weight_layout.error = "30에서 150 사이의 수를 입력하세요"
                        button.isEnabled = false
                    }
                    else { weight_layout.error = null; button.isEnabled = true }
                } catch (e: Exception) {
                    weight_layout.error = "숫자를 입력하세요"; button.isEnabled = false
                }
            }
        })
    }
}
