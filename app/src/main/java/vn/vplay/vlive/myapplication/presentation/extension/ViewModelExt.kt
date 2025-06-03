package vn.vplay.vlive.myapplication.presentation.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(): T {
    return ViewModelProvider(this)[T::class.java]
}


inline fun <reified T : ViewModel> FragmentActivity.viewModel(): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { getViewModel() }


inline fun <reified T : ViewModel> Fragment.viewModel(): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { getViewModel() }

fun ViewModel.launch(
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch {
        block()
    }
}