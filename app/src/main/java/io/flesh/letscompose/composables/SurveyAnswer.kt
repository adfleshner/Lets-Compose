package io.flesh.letscompose.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.flesh.letscompose.models.Answer
import io.flesh.letscompose.sampledata.SampleData
import io.flesh.letscompose.ui.theme.SurveyTheme

@Composable
fun SurveyAnswer(answer: Answer, isSelected: Boolean, onAnswerSelected: (Answer) -> Unit) {
    Surface(
        modifier = Modifier.padding(4.dp),
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.outline
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // if and image is set create an Image that will show the image.
            answer.image?.let { painterResource(id = it) }?.let {
                Image(
                    painter = it,
                    contentDescription = "They are the ones with the ans",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(60.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                )
            }
            Spacer(modifier = Modifier.padding(end = 12.dp))
            Text(text = answer.text, modifier = Modifier.weight(1f))
            RadioButton(selected = isSelected, onClick = {
                // invoke the onAnswerSelected with the ans when selected.
                onAnswerSelected.invoke(answer)
            })
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewSurveyAnswer() {
    SurveyTheme {
        SurveyAnswer(SampleData.answerSample, false) {

        }
    }
}