package com.mihlali.quizzapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultsOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results_one)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnFinish)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Tvscore = findViewById<TextView>(R.id.textView3)
        val btnFinish = findViewById<Button>(R.id.btnDone)

        //code Logic
        //Get the score from the intent
        val score = intent.getIntExtra("score", 0)
        val username = intent.getStringExtra("username")
        //dsplay the score and username in the textView
        //corrected variable name
        if (score< 3) {
        Tvscore.text = "Keep Practicing!,$score"} else {
            Tvscore.text = "Great Job!,$score"
        }
        btnFinish.setOnClickListener {
            finish()
        }
        }
    }//code starts here



