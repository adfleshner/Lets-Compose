package io.flesh.letscompose.places.states

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.flesh.letscompose.models.WellnessTask

// We use the remember task so that the data survives recomposition and does not recall the data
// So if it is a network call it will not be called again.
@Composable
fun WellnessTaskList(
    tasks: List<WellnessTask>,
    onTaskRemoved: (WellnessTask) -> Unit,
    onTaskChecked: (WellnessTask, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        // you add a key to the items list by creating a lambda for key and specifying the key
        // for each item. This way compose will recognize the change and move the item to the
        // correct location. Without needing to recompose all of the items.
        items(tasks, key = { task -> task.id }) { task ->
            // to get the onClose lambda to work pass the onTaskRemoved(task) in a lambda down to the
            // WellTaskItem.
            // We need to call the [onTaskRemoved(task)] here in a lambda because the onClose lambda
            // is () -> Unit and does not know anything about the task that is getting removed
            WellnessTaskItem(
                taskName = task.label,
                onClose = { onTaskRemoved(task) },
                checked = task.checked,
                onCheckChanged = { checked -> onTaskChecked(task, checked) })
        }
    }
}