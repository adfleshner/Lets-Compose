package io.flesh.letscompose.places.states

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import io.flesh.letscompose.models.WellnessTask
import io.flesh.letscompose.sampledata.SampleData

class WellnessScreenViewModel : ViewModel() {

    // this is the mutable list so we will want to keep this private
    private val _tasks =  SampleData.wellnessTasks.toMutableStateList()
    val tasks : List<WellnessTask> get() = _tasks

    fun remove(item: WellnessTask){
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        _tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
}