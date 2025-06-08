package vn.vplay.vlive.myapplication.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vn.vplay.vlive.myapplication.data.mapper.toEntity
import vn.vplay.vlive.myapplication.domain.help.StateData
import vn.vplay.vlive.myapplication.domain.help.mutableStateFlow
import vn.vplay.vlive.myapplication.domain.help.postValue
import vn.vplay.vlive.myapplication.domain.repository.ContentRepo
import vn.vplay.vlive.myapplication.presentation.model.IContentUi
import vn.vplay.vlive.myapplication.presentation.model.ISlideItemUi
import vn.vplay.vlive.myapplication.presentation.model.ISlideUi
import javax.inject.Inject

class GetHomeSlidesUseCase @Inject constructor(private val homeRepository: ContentRepo) {
    private val _data = mutableStateFlow<IContentUi>()
    val data: StateFlow<StateData<IContentUi>> = _data.asStateFlow()
    suspend operator fun invoke() {
        val list = homeRepository.getSlides()
            .data
            ?.data
            .orEmpty()
            .map { it.toEntity() }
            .map { ISlideItemUi.SlideItemUi(it) }
        val listSlide = ISlideUi.SlideUi(list)
        _data.postValue(listSlide)
    }
}