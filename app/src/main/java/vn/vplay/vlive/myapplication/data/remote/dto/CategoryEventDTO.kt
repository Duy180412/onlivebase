package vn.vplay.vlive.myapplication.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoryEventDTO(
    val id: Int,
    val name: String?,
    @SerializedName("thumbnail_1")
    val thumbnail1: String?,
    @SerializedName("thumbnail_2")
    val thumbnail2: String?,
    @SerializedName("type_id")
    val typeId: Int?,
    @SerializedName("thumbnail_no_color")
    val thumbnailNoColor: String?,
    @SerializedName("logo_active")
    val logoActive: String?,
    @SerializedName("logo_inactive")
    val logoInactive: String?,
)