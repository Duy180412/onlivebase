package vn.vplay.vlive.myapplication.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LeagueEventDTO(
    val id: String?, val logo: String?, val name: String?,
    @SerializedName("backgroud_color")
    val backgroundColor: String?,
    @SerializedName("league_sync_id")
    val leagueSyncId: String?,
    @SerializedName("category_name")
    val categoryName: String?,
    @SerializedName("type_name")
    val typeName: String?,
    val icon: String?,
    val thumbnail: String?,
    @SerializedName("thumbnail_banner")
    val thumbnailBanner: String?,
    @SerializedName("is_live")
    val isLive: Boolean?,
)