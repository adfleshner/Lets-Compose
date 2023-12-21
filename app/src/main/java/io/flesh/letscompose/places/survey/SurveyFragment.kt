package io.flesh.letscompose.places.survey

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.flesh.letscompose.utils.ComposableFragment
import io.flesh.letscompose.places.survey.composables.SurveyAnswer
import io.flesh.letscompose.models.Answer
import io.flesh.letscompose.sampledata.SampleData
import io.flesh.letscompose.ui.theme.SurveyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SurveyFragment : ComposableFragment() {

    private val viewModel : SurveyViewModel by viewModels()
    // No fucking clue if this is right
    override val saveStateId: Int
        get() = 1234

    @Composable
    override fun ComposeWorld() {
        // How do I do this in the compose world
        viewModel.answers.value?.let {
            SurveyScreen(answers = it)
        }
    }
}

@Composable
fun SurveyScreen(answers: List<Answer>) {
    var selectedAnswer : Answer? by remember { mutableStateOf(null) }
    SurveyTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(answers) { answer ->
                    SurveyAnswer(
                        answer = answer,
                        isSelected = selectedAnswer == answer,
                        onAnswerSelected = {
                            selectedAnswer = it
                        }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun SurveyPreview() {
    SurveyScreen(SampleData.answersSample)
}


class SurveyViewModel : ViewModel() {

    val answers: MutableLiveData<List<Answer>> = MutableLiveData(SampleData.answersSample)

    private val _uiState : MutableStateFlow<Any> = MutableStateFlow(Any())
    val uiState : StateFlow<Any> = _uiState.asStateFlow()

}