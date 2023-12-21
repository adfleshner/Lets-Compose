package io.flesh.letscompose.places.states

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

// you are able to add the ViewModel by hoisting the ViewModel into a parameter.
// you would only use viewmodel at screen level Composable.
// This is actaully probably a bad idea and we should move the ViewModel up to the activity or
// fragment layer and only pass the state and lambdas to the screen to make Testing the Screen
// Composable much easier.
@Composable
fun WellnessScreen(
    wellnessScreenViewModel: WellnessScreenViewModel = viewModel(),
    modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        StatefulWaterCounter()
        // toMutableStateList creates a snapshot of the list
//        val tasks = remember { SampleData.wellnessTasks.toMutableStateList() }
        // and the line above can also be written like
        // again basically makes a copy of the list.
        // MutableStateList is : An implementation of MutableList that can be observed and snapshot.
        // if writing list this below be sure to add everything in the remember lambda otherwise the
        // data will get re added to the list everytime the Composable Recomposes.
//        val list = remember {
//            mutableStateListOf<WellnessTask>().apply {
//                addAll(SampleData.wellnessTasks)
//            }
//        }
        val tasks = wellnessScreenViewModel.tasks
        WellnessTaskList(tasks = tasks, onTaskRemoved = {
            task -> wellnessScreenViewModel.remove(task)
        }, onTaskChecked = { task , checked ->
            wellnessScreenViewModel.changeTaskChecked(task,checked)
        })
    }
}
