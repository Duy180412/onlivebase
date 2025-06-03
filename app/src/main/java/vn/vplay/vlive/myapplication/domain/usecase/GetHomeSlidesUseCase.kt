package vn.vplay.vlive.myapplication.domain.usecase

import vn.vplay.vlive.myapplication.data.mapper.toEntity
import vn.vplay.vlive.myapplication.domain.entity.HomeContentUi
import vn.vplay.vlive.myapplication.domain.repository.ContentRepo
import vn.vplay.vlive.myapplication.presentation.help.SingleLiveEvent
import vn.vplay.vlive.myapplication.presentation.model.IHomeContentUi
import javax.inject.Inject

class GetHomeSlidesUseCase @Inject constructor(private val homeRepository: ContentRepo) {
    val data = SingleLiveEvent<IHomeContentUi>()
    suspend operator fun invoke() {
        val data = homeRepository.getSlides().data?.data.orEmpty().map { it.toEntity() }


    }
}