package io.flesh.letscompose.places.survey

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.flesh.letscompose.places.survey.composables.SurveyAnswer
import io.flesh.letscompose.models.Answer
import io.flesh.letscompose.sampledata.SampleData
import io.flesh.letscompose.utils.toast
import io.flesh.letscompose.ui.theme.LetsComposeTheme

class SurveyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SingleChoiceQuestions()
                }
            }
        }
    }
}


//region Composables
@Composable
fun SingleChoiceQuestions(answers: List<Answer> = SampleData.answersSample){
    val context : Context = LocalContext.current
    // what is the difference between by and = when using remember.
    // also WTF is rememberSavable.
    // OOOOOOO by is a Delegate that makes it so that I can unwrap the MutableState and just worry
    // about the Answer.
    var selectedAnswer : Answer? by remember {
        mutableStateOf(null)
    }

    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column {
            // you just put a fucking for loop and create composables? whaaa?
            if (answers.isEmpty()) {
                // Show Empty Composable
                Column(modifier = Modifier
                    .padding(16.dp) // add padding
                    .fillMaxSize(),  // then make it fill the screen
                    verticalArrangement = Arrangement.Center, // use arrangement in to center
                    horizontalAlignment = Alignment.CenterHorizontally // use arrangement in to center
                ) {
                    Text(text = "No Questions Here")
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(onClick = {
                        context.toast {
                            "You Tried"
                        }
                    }) {
                        Text(text = "Try Again")
                    }
                }
            }
            else {
                answers.forEach { answer ->
                    SurveyAnswer(answer = answer,
                        isSelected = (selectedAnswer == answer),
                        onAnswerSelected = { newAnswer ->
                            // this updates the selected answer in this composable when a item is selected.
                            selectedAnswer = newAnswer
                        }
                    )
                }
            }
        }
    }
}
//endregion

//region PREVIEWS
@Preview(showBackground = true , uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun SingleChoicePreview() {
    LetsComposeTheme {
        SingleChoiceQuestions()
    }
}//endregion