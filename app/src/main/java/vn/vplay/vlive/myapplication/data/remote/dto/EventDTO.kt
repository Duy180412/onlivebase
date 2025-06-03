package vn.vplay.vlive.myapplication.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class EventDTO(
    val id: String?,
    var name: String?,
    var description: String?,
    val status: String?,
    var thumbnail: String,
    @SerializedName("thumbnail_horizontal")
    var thumbnailHorizontal: String?,
    @SerializedName("thumbnail_vertical")
    var thumbnailVertical: String?,
    val attribute: AttributeDTO?,
    @SerializedName("start_time")
    val startTime: String?,
    @SerializedName("over_time")
    val overTime: String?,
    val duration: Int?,
    val created: String?,
    @SerializedName("is_protected")
    val isProtected: Boolean?,
    @SerializedName("free_content")
    val freeContent: Boolean?,
    val url: String?,
    val type: Int?,
    @SerializedName("livescore_type")
    val liveScoreType: Int?,
    @SerializedName("match_sync_id")
    val matchId: String?,
    @SerializedName("league_name")
    val leagueName: String?,
    @SerializedName("category_name")
    var categoryName: String?,
    val location: String?,
    @SerializedName("is_noticed")
    var isNoticed: Boolean,
    @SerializedName("block_type")
    val blockType: Int?,
    @SerializedName("block_display_type")
    val blockDisplayType: Int?,
    @SerializedName("is_premium")
    val isPremium: Boolean = false,
    @SerializedName("package_type")
    val packageType: Int?,
    @SerializedName("is_monopoly")
    val isMonopoly: Boolean,
    @SerializedName("is_url_external")
    val isUrlExternal: Boolean,
    @SerializedName("url_external")
    val urlExternal: String?,
    val ratio: String?,
    @SerializedName("league")
    val league: LeagueEventDTO?,
    @SerializedName("league_id")
    val leagueId: String? = "",
    @SerializedName("category_id")
    val categoryId: String? = "",
    @SerializedName("league_logo")
    val leagueLogo: String? = "",
    @SerializedName("thumbnail_category_banner")
    val thumbnailCategoryBanner: String = "",
    @SerializedName("color")
    val color: String?,
    @SerializedName("categories")
    val categories: CategoryEventDTO?,
    @SerializedName("descriptions")
    val descriptions: String?
)