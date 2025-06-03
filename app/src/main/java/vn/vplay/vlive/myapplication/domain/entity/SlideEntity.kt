package vn.vplay.vlive.myapplication.domain.entity

import vn.vplay.vlive.myapplication.data.enumext.StatusEvent
import vn.vplay.vlive.myapplication.data.remote.dto.SlideDTO

interface ISideEntity {
    val id: String
    val name: String
    val imageUrl: String
    val logo: String
    val leagueName: String
    val color: String

    class SlideEntity(slideDTO: SlideDTO): ISideEntity {
        override val id: String = slideDTO.id.orEmpty()
        override val name: String = slideDTO.name.orEmpty()
        override val imageUrl: String = slideDTO.thumbnail.orEmpty()
        override val logo: String = slideDTO.logo.orEmpty()
        override val leagueName: String = slideDTO.leagueName.orEmpty()
        override val color: String = slideDTO.color.orEmpty()
    }
}

interface IVodSlideEntity : IEventEntity, ISideEntity {
    val startTime: String
    val duration: String
}

interface ILiveSlideEntity : IEventEntity, ISideEntity {
    val statusEvent: StatusEvent
    val startTime: String
    val endTime: String
}

interface IWebSlideEntity : IEventEntity, ISideEntity {
     val urlWeb: String
}