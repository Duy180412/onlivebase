package vn.vplay.vlive.myapplication.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DefaultResponse<T>(
    @Expose
    @SerializedName("data")
    val data: T?,
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("error_message")
    val errorMessage: String?,
    val success: Boolean?
)

data class PagingResponse<T>(
    @Expose
    @SerializedName("data")
    val data: T?,
    @SerializedName("total_page")
    val totalPage: Int?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("page_num")
    val pageNum: Int?,
    @SerializedName("page_size")
    val pageSize: Int?,
    @SerializedName("has_previous")
    val hasPrevious: Boolean?,
    @SerializedName("has_next")
    val hasNext: Boolean?,
    @SerializedName("tab")
    val tab: Int?,
)