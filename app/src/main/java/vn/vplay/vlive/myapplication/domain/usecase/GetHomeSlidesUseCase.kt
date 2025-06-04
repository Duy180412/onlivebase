package vn.vplay.vlive.myapplication.domain.usecase

import vn.vplay.vlive.myapplication.data.mapper.toEntity
import vn.vplay.vlive.myapplication.domain.entity.IEventEntity
import vn.vplay.vlive.myapplication.domain.entity.ILiveSlideEntity
import vn.vplay.vlive.myapplication.domain.entity.ISideEntity
import vn.vplay.vlive.myapplication.domain.entity.IVodSlideEntity
import vn.vplay.vlive.myapplication.domain.repository.ContentRepo
import vn.vplay.vlive.myapplication.presentation.extension.cast
import vn.vplay.vlive.myapplication.presentation.help.SingleLiveEvent
import vn.vplay.vlive.myapplication.presentation.model.IContentUi
import vn.vplay.vlive.myapplication.presentation.model.ISlideUi
import javax.inject.Inject

class GetHomeSlidesUseCase @Inject constructor(private val homeRepository: ContentRepo) {
    val data = SingleLiveEvent<List<IContentUi>>()
    suspend operator fun invoke() {
        val list =  homeRepository.getSlides()
            .data
            ?.data
            .orEmpty()
            .map { it.toEntity() }
            .map { ISlideUi.SlideUi(it) }
        data.postValue(list)
    }

}