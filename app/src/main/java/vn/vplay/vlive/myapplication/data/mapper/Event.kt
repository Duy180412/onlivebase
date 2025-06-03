package vn.vplay.vlive.myapplication.data.mapper

import vn.vplay.vlive.myapplication.data.enumext.StatusEvent
import vn.vplay.vlive.myapplication.presentation.help.parseToDateUtc7

fun String?.toStatusEventOrTime(startTime: String?, overTime: String?): StatusEvent {
    return when (this) {
        "live" -> StatusEvent.Live
        "not_start" -> StatusEvent.NotStart
        "finnish" -> StatusEvent.Finnish
        else -> statusEventByTime(startTime, overTime)
    }
}

fun statusEventByTime(startTime: String?, overTime: String?): StatusEvent {
    val dateStartTime = startTime?.parseToDateUtc7() ?: return StatusEvent.None
    val dateEndTime = overTime?.parseToDateUtc7() ?: return StatusEvent.None
    val currentTime = System.currentTimeMillis()
    return when {
        currentTime < dateStartTime.time -> StatusEvent.NotStart
        currentTime <= dateEndTime.time -> StatusEvent.Live
        else -> StatusEvent.None
    }
}