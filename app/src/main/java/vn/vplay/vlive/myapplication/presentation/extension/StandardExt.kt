package vn.vplay.vlive.myapplication.presentation.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

inline fun <reified T> Any?.cast(): T? {
    if (this is T) return this
    return null
}