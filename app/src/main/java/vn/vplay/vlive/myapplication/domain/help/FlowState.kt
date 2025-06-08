package vn.vplay.vlive.myapplication.domain.help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest


inline fun <reified T> mutableStateFlow(): MutableStateFlow<StateData<T>> =
    MutableStateFlow(StateData.Loading as StateData<T>)

fun <T> MutableStateFlow<StateData<T>>.postLoading() {
    value = StateData.Loading
}

fun <T> MutableStateFlow<StateData<T>>.postValue(data: T) {
    value = StateData.Success(data)
}

val <T> StateFlow<StateData<T>>.data: T?
    get() = (value as? StateData.Success<T>)?.data


@Composable
fun <T> StateFlow<StateData<T>>.collectStateData(): State<StateData<T>> {
    return collectAsState(initial = StateData.Loading as StateData<T>)
}

@Composable
fun <T> StateFlow<StateData<T>>.collectData(): State<T?> {
    return produceState<T?>(initialValue = null, this) {
        this@collectData.collectLatest {
            if (it is StateData.Success<T>) {
                value = it.data
            }
        }
    }
}

sealed class StateData<out T> {
    data object Loading : StateData<Nothing>()
    data class Success<T>(val data: T) : StateData<T>()
    data class Error(val message: String) : StateData<Nothing>()
}