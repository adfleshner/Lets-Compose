package io.flesh.letscompose.places.states

import android.util.Log
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import io.flesh.letscompose.R

// A composable that has an internal state is considered a stateful composable which are less
// reusable and harder to test.

// A composable that does not hold any state is considered a stateless composable which are more
// reusable and easier to test

// A way to make composable's stateless is to use State Hoisting which moves the state out of the
// composable
@Composable
fun StatefulWaterCounter(modifier: Modifier = Modifier) {
    // remembered states
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    var checked by rememberSaveable {
        mutableStateOf(false)
    }

    // --------------------------------------------------
    StatelessWaterCounter(
        count = count,
        onIncrement = {
            count++
        },
        checked = checked,
        onCheckChanged = { newValue -> checked = newValue },
        onClear = {
            count = 0
        }, modifier = modifier
    )
}

// A composable that does not hold any state is considered a stateless composable which are more
// reusable and easier to test

// A way to make composable's stateless is to use State Hoisting which moves the state out of the
// composable


@Composable
private fun StatelessWaterCounter(
    count: Int,
    checked: Boolean,
    onIncrement: () -> Unit,
    onClear: () -> Unit,
    onCheckChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var showTask by remember {
        mutableStateOf(true)
    }

    // resources for composable
    val wellnessTaskText = stringResource(id = R.string.wellness_walk_task)
    val waterCounterPadding = dimensionResource(id = R.dimen.water_counter_padding)
    val waterCounterStartButtonPadding =
        dimensionResource(id = R.dimen.water_counter_button_padding)
    val waterCounterTopButtonPadding =
        dimensionResource(id = R.dimen.water_counter_top_button_padding)

    //Composable Views
    Column(modifier = modifier.padding(waterCounterPadding)) {
        val glassesCount = puralStringHandlingZero(
            R.string.glasses_count_zero,
            R.plurals.glasses_count,
            count,
            count
        )
        if (count > 0) {
            if (showTask) {
                WellnessTaskItem(
                    taskName = wellnessTaskText,
                    checked = checked,
                    onClose = { showTask = false },
                    onCheckChanged = onCheckChanged
                )
            }
            Text(
                text = glassesCount
            )
        }
        Row(modifier = Modifier.padding(top = waterCounterTopButtonPadding)) {
            Button(
                onClick = { onIncrement.invoke() },
                enabled = count < 10,
            ) {
                Text(text = stringResource(id = R.string.add_one))
            }
            val isClearEnabled = count != 0
            Log.d("HELLO!", "IsEnabled = $isClearEnabled count = $count")
            Button(
                onClick = { onClear.invoke() },
                enabled = isClearEnabled,
                modifier = Modifier.padding(start = waterCounterStartButtonPadding)
            ) {
                Text(text = stringResource(id = R.string.clear_water_count))
            }
        }
    }
}


@Composable
fun puralStringHandlingZero(
    @StringRes zeroId: Int,
    @PluralsRes id: Int,
    count: Int,
    amount: Int
): String {
    return if (count == 0) {
        stringResource(id = zeroId)
    } else {
        pluralStringResource(id = id, count = count, amount)
    }
}

