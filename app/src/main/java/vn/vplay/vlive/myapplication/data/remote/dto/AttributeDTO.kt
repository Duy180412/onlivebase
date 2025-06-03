package vn.vplay.vlive.myapplication.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AttributeDTO(
    @SerializedName("livescore_type")
    val liveScoreType: Int?,
    @SerializedName("away_thumbnail")
    val awayLogo: String?,
    @SerializedName("away_logo")
    val awayLogoOld: String?,
    @SerializedName("away_name")
    val awayName: String?,
    @SerializedName("away_score")
    val awayScore: Int?,
    @SerializedName("home_thumbnail")
    val homeLogo: String?,
    @SerializedName("home_logo")
    val homeLogoOld: String?,
    @SerializedName("home_name")
    val homeName: String?,
    @SerializedName("home_score")
    val homeScore: Int?,
    @SerializedName("number_of_teams")
    val numberOfTeams: Int?,
    @SerializedName("number_of_matches")
    val numberOfMatches: Int?,
    @SerializedName("match_time")
    val matchTime: String?,
    @SerializedName("league_id")
    val leagueId: String?,
    @SerializedName("league_name")
    val leagueName: String?,
    @SerializedName("top1_team")
    val top1Team: String?,
    @SerializedName("top2_team")
    val top2Team: String?,
    @SerializedName("top3_team")
    val top3Team: String?,
)