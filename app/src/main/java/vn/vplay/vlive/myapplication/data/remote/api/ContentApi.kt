package vn.vplay.vlive.myapplication.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import vn.vplay.vlive.myapplication.data.remote.ApiEndPoint
import vn.vplay.vlive.myapplication.data.remote.dto.SlideDTO
import vn.vplay.vlive.myapplication.data.remote.response.DefaultResponse


interface ContentApi {
    @GET(ApiEndPoint.HOME_BANNER)
    suspend fun getSlides(): Response<DefaultResponse<List<SlideDTO>>>
}


