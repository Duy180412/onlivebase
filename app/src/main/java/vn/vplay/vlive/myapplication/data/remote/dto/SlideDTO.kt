package vn.vplay.vlive.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SlideDTO(
    val id: String,
    val name: String,
    @SerializedName("thumbnail_mobile")
    val thumbnail: String,
    val event: EventDTO?,
    val link: String?,
    @SerializedName("is_premium")
    val isPremium: Boolean = false,
    @SerializedName("is_monopoly")
    val isMonopoly: Boolean,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("league_name")
    val leagueName: String,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("type_link")
    val typeLink: Int,
    @SerializedName("league_id")
    val leagueId: String,
    @SerializedName("color")
    var color: String?,
    @SerializedName("additional_image")
    var logo: String?,
)