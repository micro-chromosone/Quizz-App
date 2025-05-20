package com.mihlali.quizzapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textTitle2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //code starts here
        val textQuestion = findViewById<TextView>(R.id.textQuestion)
        val radioButtonQuizAnswers = findViewById<RadioGroup>(R.id.radioButtonQuizAnswers)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val username = intent.getStringExtra("username")

        //creating array with 5 questions
        val HistoryQuestions = arrayOf(
            "Nelson Mandela was the first black president of South Africa?",
            "Apartheid Ended in the early 1990s in South Africa?",
            "The Group Areas Act is what Geographically separated South Africans based on Race",
            "leon Schuster was once the president of South Africa?",
            "Apartheid lasted 50 Years"

        )
        val HistoryAnswers = arrayOf(
            arrayOf("A: False", "B: Skip", "C:True"),
            arrayOf("A: False", "B: True", "C: Skip"),
            arrayOf("A: True", "B: Skip", "C: False"),
            arrayOf("A: True", "B: Skip", "C: False"),
            arrayOf("A: False", "B: True", "C: Skip")
        )

        val correctAnswers = arrayOf(
            "C",
            "B",
            "A",
            "C",
            "A"
        )
        var useranswers = arrayOfNulls<String>(5)

        var counter = 0
        textQuestion.text = HistoryQuestions[counter]
        for (i in 0 until radioButtonQuizAnswers.childCount) {
            val radioButton = radioButtonQuizAnswers.getChildAt(i) as RadioButton
            radioButton.text = HistoryAnswers[counter][i]
        }
        btnSubmit.setOnClickListener {
            if (counter < 5) {
                val selected = radioButtonQuizAnswers.checkedRadioButtonId
                if (selected != -1) {
                    val selectedRbtn = findViewById<RadioButton>(selected)
                    useranswers[counter] = selectedRbtn.text.toString()
                    counter++
                } else {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener //stops the execution if no one answer is selected
                }

                if (counter < 5) {
                    textQuestion.text = HistoryQuestions[counter]
                    for (i in 0 until radioButtonQuizAnswers.childCount) {
                        val radioButton = radioButtonQuizAnswers.getChildAt(i) as RadioButton
                        radioButton.text = HistoryAnswers[counter][i]

                    }
                    radioButtonQuizAnswers.clearCheck()

                } else {
                    var intent = Intent(this, ResultsOne::class.java)
                    var score = 0
                    //calculate score
                    for (i in useranswers.indices) {
                        if (useranswers[i] == correctAnswers[i]) {
                            score++
                        }
                    }
                    intent.putExtra("score", score)
                    intent.putExtra("username",username)
                    startActivity(intent)
                    finish()
                }

            }

        }


    }//end of onCreate
}//end of Quiz Activity