package io.flesh.letscompose.places.states

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.flesh.letscompose.R

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onClose: () -> Unit,
    onCheckChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // Use rememberSaveable in this matter as well to ensure that the sate is remembered in a list.
    // as well as during a configuration change.
    // Moving this to the viewModel
//    var checkedState by rememberSaveable {
//        mutableStateOf(false)
//    }
    StatelessWellnessTaskItem(
        taskName = taskName,
        checked = checked,
        onClose = onClose,
        onCheckChanged = onCheckChanged,
        modifier = modifier
    )
}

@Composable
fun StatelessWellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onClose: () -> Unit,
    onCheckChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val startPadding = dimensionResource(id = R.dimen.wellness_text_start_padding)
    val closeButtonContentDescription = stringResource(id = R.string.close)
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = startPadding), text = taskName
        )
        Checkbox(checked = checked, onCheckedChange = onCheckChanged)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = closeButtonContentDescription)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WellnessTaskPreview() {
    WellnessTaskItem(taskName = "This is a Task", checked = false,
        onClose = {
            // Do nothing you are a preview
        },
        onCheckChanged = {
            // Do nothing you are a preview
        })
}