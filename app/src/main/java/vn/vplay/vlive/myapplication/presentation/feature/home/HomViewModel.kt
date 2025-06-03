package vn.vplay.vlive.myapplication.presentation.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import vn.vplay.vlive.myapplication.domain.usecase.GetHomeSlidesUseCase
import vn.vplay.vlive.myapplication.presentation.extension.launch
import javax.inject.Inject

@HiltViewModel
class HomViewModel @Inject constructor(
    private val getHomeSlidesUseCase: GetHomeSlidesUseCase
) : ViewModel() {
    val slides = getHomeSlidesUseCase.data

    fun featSlides() = launch {
        getHomeSlidesUseCase()
    }
}