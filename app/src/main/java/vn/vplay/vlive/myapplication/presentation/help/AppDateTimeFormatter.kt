package vn.vplay.vlive.myapplication.presentation.help

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

val listTime: List<Pair<String, String>> = listOf(
    "yyyy-MM-dd'T'HH:mm:ss.SSS" to "UTC",
    "yyyy-MM-dd'T'HH:mm:ss'Z'" to "UTC",
    "yyyy-MM-dd HH:mm:ss" to "GMT+07:00"
)

fun String?.parseToDateUtcNormalized(): Date? {
    if (this.isNullOrEmpty()) return null
    listTime.forEach { (pattern, tzId) ->
        try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone(tzId)
                isLenient = false
            }
            val parsed = sdf.parse(this) ?: return@forEach
            val sourceOffsetMs = sdf.timeZone.rawOffset.toLong()
            return Date(parsed.time - sourceOffsetMs)
        } catch (_: ParseException) {
        }
    }
    return null
}

fun String?.parseToDateUtc7(): Date? {
    val dateUtc = this?.parseToDateUtcNormalized()
    dateUtc ?: return null
    val calendar = Calendar.getInstance()
    calendar.time = dateUtc
    calendar.add(Calendar.HOUR, 7)
    return calendar.time
}

fun Int?.toTimeString(): String {
    if (this == null) return ""
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

fun String?.toDdMmYyyy(): String? {
    if (this.isNullOrBlank()) return null
    val dateUtcPlus7 = this.parseToDateUtcNormalized() ?: return null
    return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).apply {
        isLenient = false
    }.format(dateUtcPlus7)
}

fun String?.toDayMothYear(): String? {
    if (this.isNullOrBlank()) return null
    val dateUtcPlus7 = this.parseToDateUtcNormalized() ?: return null
    return SimpleDateFormat("dd 'tháng' MM ',' yyyy", Locale.getDefault()).apply {
        isLenient = false
    }.format(dateUtcPlus7)
}

fun String?.toTimeDayDdMm(): String? {
    if (this.isNullOrBlank()) return null
    val dateUtcPlus7 = this.parseToDateUtcNormalized() ?: return null
    return SimpleDateFormat("HH:mm 'ngày' dd/MM", Locale.getDefault()).apply {
        isLenient = false
    }.format(dateUtcPlus7)
}

fun String?.toLongTime(): Long? {
    if (this.isNullOrBlank()) return null
    val dateUtc7 = this.parseToDateUtc7()
    return dateUtc7?.time
}


fun Date.isToday(): Boolean {
    val today = Calendar.getInstance()
    val target = Calendar.getInstance()
    target.time = this
    return today.get(Calendar.YEAR) == target.get(Calendar.YEAR) &&
            today.get(Calendar.DAY_OF_YEAR) == target.get(Calendar.DAY_OF_YEAR)
}

fun Date.toDdMmYyyy(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
        isLenient = false
    }
    return formatter.format(this)
}

fun Date.toYyyyMmDd(): String {
    try {
        val format = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
        return format.format(this)
    } catch (e: java.lang.Exception) {
        return ""
    }
}

fun String?.toDdMm(): String? {
    if (this.isNullOrBlank()) return null
    val dateUtcPlus7 = this.parseToDateUtcNormalized() ?: return null
    return SimpleDateFormat("dd/MM", Locale.getDefault()).apply {
        isLenient = false
    }.format(dateUtcPlus7)
}

fun String?.toHhMm(): String? {
    if (this.isNullOrBlank()) return null
    val dateUtcPlus7 = this.parseToDateUtcNormalized() ?: return null
    return SimpleDateFormat("HH:mm", Locale.getDefault()).apply {
        isLenient = false
    }.format(dateUtcPlus7)
}

fun String?.toHhMmDayDdMmYyyy(): String? {
    if (this.isNullOrBlank()) return null
    val dateUtcPlus7 = this.parseToDateUtcNormalized() ?: return null
    return SimpleDateFormat("HH:mm 'ngày' dd/MM/yyyy", Locale.getDefault()).apply {
        isLenient = false
    }.format(dateUtcPlus7)
}

fun String?.toHhMmDdMmYyyy(): String? {
    if (this.isNullOrBlank()) return null
    val dateUtcPlus7 = this.parseToDateUtcNormalized() ?: return null
    return SimpleDateFormat("HH:mm ' - ' dd/MM/yyyy", Locale.getDefault()).apply {
        isLenient = false
    }.format(dateUtcPlus7)
}

@SuppressLint("DefaultLocale")
fun Long.toTimeStringFromMillis(): String {
    val totalSeconds = this / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

@SuppressLint("DefaultLocale")
fun Int.toTimeStringFromSeconds(): String {
    val totalSeconds = this.toLong()
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
