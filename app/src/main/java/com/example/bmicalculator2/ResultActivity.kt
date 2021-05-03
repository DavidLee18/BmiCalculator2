package com.example.bmicalculator2

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.layout_result.*
import org.jetbrains.anko.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val pref = getSharedPreferences("bmi2", Context.MODE_PRIVATE)
        val height = intent.getDoubleExtra("height", 0.0)
        val weight = intent.getDoubleExtra("weight", 0.0)
        val bmi by lazy {
            try {
                weight * 10000 / (height * height)
            } catch (e: Exception) {
                0.0
            }
        }
        setContentView(R.layout.layout_result)
        imageView.setImageResource(
            when (bmi) {
                in 18.5..25.0 -> R.drawable.ic_sentiment_satisfied_black_24dp
                in 25.1..30.0 -> R.drawable.ic_sentiment_neutral_black_24dp
                in 30.1..40.0 -> R.drawable.ic_sentiment_dissatisfied_black_24dp
                else -> R.drawable.ic_sentiment_very_dissatisfied_black_24dp
            }
        )
        textView.text = when (bmi) {
            in 18.5..25.0 -> "정상"
            in 25.1..30.0 -> "과체중"
            in 30.1..40.0 -> "비만"
            else -> "???"
        }
        Snackbar.make(
            result, when (bmi) {
                in 18.5..25.0 -> "정상"
                in 25.1..30.0 -> "과체중"
                in 30.1..40.0 -> "비만"
                else -> "???"
            }, Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
