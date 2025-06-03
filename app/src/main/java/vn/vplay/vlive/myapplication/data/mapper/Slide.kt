package vn.vplay.vlive.myapplication.data.mapper

import android.system.Os.bind
import vn.vplay.vlive.myapplication.data.enumext.StatusEvent
import vn.vplay.vlive.myapplication.data.remote.dto.SlideDTO
import vn.vplay.vlive.myapplication.domain.entity.IEventEntity
import vn.vplay.vlive.myapplication.domain.entity.ILiveSlideEntity
import vn.vplay.vlive.myapplication.domain.entity.ISideEntity
import vn.vplay.vlive.myapplication.domain.entity.IVodSlideEntity
import vn.vplay.vlive.myapplication.domain.entity.IWebSlideEntity
import vn.vplay.vlive.myapplication.presentation.help.toDdMmYyyy
import vn.vplay.vlive.myapplication.presentation.help.toTimeString

fun SlideDTO.toEntity(): ISideEntity {
    return when (this.typeLink) {
        1 -> object : ILiveSlideEntity,
            IEventEntity by IEventEntity.EventEntity(this@toEntity.event),
            ISideEntity by ISideEntity.SlideEntity(this@toEntity) {
            override val statusEvent: StatusEvent = this@toEntity.event?.status.toStatusEventOrTime(
                this@toEntity.event?.startTime,
                this@toEntity.event?.overTime
            )
            override val startTime: String = this@toEntity.event?.startTime.toDdMmYyyy().orEmpty()
            override val endTime: String = this@toEntity.event?.overTime.toDdMmYyyy().orEmpty()
        }

        2 -> {
            object : IVodSlideEntity,
                IEventEntity by IEventEntity.EventEntity(this@toEntity.event),
                ISideEntity by ISideEntity.SlideEntity(this@toEntity) {
                override val startTime: String =
                    this@toEntity.event?.startTime.toDdMmYyyy().orEmpty()
                override val duration: String =
                    this@toEntity.event?.duration?.toTimeString().orEmpty()

            }
        }

        else -> {
            object : IWebSlideEntity,
                IEventEntity by IEventEntity.EventEntity(this@toEntity.event),
                ISideEntity by ISideEntity.SlideEntity(this@toEntity) {
                    override val urlWeb: String = this@toEntity.link.orEmpty()
            }
        }
    }
}