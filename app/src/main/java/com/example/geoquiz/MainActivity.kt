package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           GeoQuizApp()
            }
        }

    @Composable
    fun GeoQuizApp(){
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                QuizScreen()
            }
        }
    }

    data class Question(val text: String, val answer: Boolean)
    val questions = listOf(
        Question("Canberra is the capital of Australia.", true),
        Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        Question("The source of the Nile River is in Egypt.", false),
        Question("The Amazon River is the longest river in the Americas.", true),
        Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )

    @Composable
    fun QuizScreen() {
        var index by remember { mutableStateOf(0) }
        val q = questions[index]
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("GeoQuiz", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(40.dp))
            Text(q.text, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(60.dp))

            var answered by remember { mutableStateOf(false) }
            var score by remember { mutableStateOf(0) }
            if (!answered) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        if (q.answer) score++
                        answered = true
                    }) {
                        Text("TRUE")
                    }

                    Button(onClick = {
                        if (!q.answer) score++
                        answered = true
                    }) {
                        Text("FALSE")
                    }
                }
            }

            Spacer(Modifier.height(40.dp))
            var quizFinished by remember { mutableStateOf(false) }
            if(!quizFinished) {
                Button(
                    onClick = {
                        if (index < questions.lastIndex) {
                            index++
                            answered = false
                        }
                        if (index == questions.lastIndex) {
                            quizFinished = true
                        }
                    },
                    enabled = answered,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("NEXT")
                }
            }
        }

    }
}

